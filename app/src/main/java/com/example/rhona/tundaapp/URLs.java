package com.example.rhona.tundaapp;

/**
 * Created by Rhona on 3/27/2019.
 */
public class URLs {
    private static final String BASEDOMAIN ="http://192.168.43.74:8080/";
    private static final String ANOTHER_ROOT_URL =BASEDOMAIN+"MyTundaApp/Api.php?apicall=";
    private static final String ROOT_URL = BASEDOMAIN+"MyTundaApp/registrationapi.php?apicall=";
    public static final String URL_REGISTER = ROOT_URL + "signup";
    public static final String URL_LOGIN= ROOT_URL + "login";
    public static final String URL_BUYERSIGNUP= ROOT_URL + "signupbuyer";
    public static final String URL_BUYERLOGIN= ROOT_URL + "loginbuyer";
    public static final String URL_UPDATEPROFILE= ROOT_URL + "editProfile";
    public static final String URL_PRODUCTLIST= ANOTHER_ROOT_URL + "getallimages";
    public static final String URL_SELLERPRODUCTS= ANOTHER_ROOT_URL + "getsellerimages";
    public static final String URL_UPLOADIMAGE =BASEDOMAIN+"MyTundaApp/capture_img_upload_to_server.php" ;

}
