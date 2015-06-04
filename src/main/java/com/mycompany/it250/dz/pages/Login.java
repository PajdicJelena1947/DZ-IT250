package com.mycompany.it250.dz.pages;

import com.mycompany.it250.dz.dao.KorisnikDao;
import com.mycompany.it250.dz.entities.Korisnik;
import java.security.NoSuchAlgorithmException;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;



public class Login
{
@Inject
 private KorisnikDao userDao;
 @Property
 private Korisnik userLogin;
 @SessionState
 private Korisnik loggedInUser;
 @Component
 private BeanEditForm form;
 Object onActivate() {
 if (loggedInUser.getEmail()!= null) {
 return Index.class;
 }
 return null;
 }
 public String getMD5Hash(String yourString) throws NoSuchAlgorithmException {
 try {
 java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");


 byte[] array = md.digest(yourString.getBytes());
 StringBuffer sb = new StringBuffer();
 for (int i = 0; i < array.length; ++i) {
 sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
 }
 return sb.toString();
 } catch (Exception e) {
 return "";
 }
 }
 Object onSuccess() throws NoSuchAlgorithmException {
 String password = getMD5Hash(userLogin.getPassword());
 System.out.println(password);
 Korisnik u = userDao.check(userLogin.getEmail(), password);
 if (u!=null) {
 loggedInUser = u;
 System.out.println("Logovan");
 return Index.class;
 } else {
 form.recordError("Uneli ste pogre�ne parametre");
 System.out.println("losi parametri");
 return null;
 }
 }
}

