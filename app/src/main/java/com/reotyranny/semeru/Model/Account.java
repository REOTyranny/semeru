package com.reotyranny.semeru.Model;
import java.security.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public abstract class Account {
    protected String name;
    protected String username;
    protected String password;
    protected String email;
    protected boolean locked ;
    protected byte[] salt;

    public void setSalt() throws NoSuchProviderException, NoSuchAlgorithmException {
        this.salt = makeSalt();
    }
    public byte[] getSalt(){
        return salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account(String name, String username, String password, String email ){
        this(name,username,password,email,false);
    }
    public Account(String name, String username, String password, String email, boolean locked)
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.locked = locked;
        try {
            setSalt();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static byte[] makeSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }

    private static String getSecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    public boolean validatePass(String password2){
        return (getSecurePassword(password2,salt) == getSecurePassword(password,salt));
    }
}
