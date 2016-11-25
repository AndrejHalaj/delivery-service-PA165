package cz.muni.fi.pa165.deliveryservice.service.impl;

import cz.muni.fi.pa165.deliveryservice.dao.CustomerDao;
import cz.muni.fi.pa165.deliveryservice.model.Customer;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;
import java.math.BigInteger;
import java.security.SecureRandom;

import java.util.Collection;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kristian Mateka
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerDao customerDao;

    @Override
    public void register(Customer customer, String password) {
        customer.setPasswordHash(createHash(password));
        
        try {
            customerDao.create(customer);
        } catch (Exception ex) {
            throw new DataAccessException("Exception while creating: " + ex.getMessage()) {
            };
        }
    }

    @Override
    public void update(Customer customer) {

        try {
            customerDao.update(customer);
        } catch (Exception ex) {
            throw new DataAccessException("Exception while updating: " + ex.getMessage()) {
            };
        }
    }

    @Override
    public void delete(Long id) {
        try {
            customerDao.delete(customerDao.findById(id));
        } catch (Exception ex) {
            throw new DataAccessException("Exception while deleting: " + ex.getMessage()) {
            };
        }
        customerDao.delete(customerDao.findById(id));
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        try {
            return customerDao.findAll();
        } catch (Exception ex) {
            throw new DataAccessException("Exception while geting: " + ex.getMessage()) {
            };
        }
    }

    @Override
    public Customer getCustomerById(Long id) {
        try {
            return customerDao.findById(id);
        } catch (Exception ex) {
            throw new DataAccessException("Exception while geting: " + ex.getMessage()) {
            };
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) {

        try {
            return customerDao.findByEmail(email);
        } catch (Exception ex) {
            throw new DataAccessException("Exception while geting: " + ex.getMessage()) {
            };
        }
    }

    @Override
    public boolean authenticate(Customer customer, String password) {
        if (customer == null) {
            return false;
        }
        try {
            return validatePassword(password, customer.getPasswordHash());
        } catch (Exception ex) {
            throw new DataAccessException("Exception while authenticating: " + ex.getMessage()) {
            };
        }
    }

    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validatePassword(String password, String correctHash) {
        if (password == null) {
            return false;
        }
        if (correctHash == null) {
            throw new IllegalArgumentException("password hash is null");
        }
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }
}
