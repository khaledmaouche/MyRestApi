package tweetsTest;
import com.sun.media.sound.InvalidFormatException;
import io.restassured.response.ValidatableResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweets.Tweets;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class TweetsTest {
    private Tweets tweets;
    @BeforeClass
    public void setUpTweetAPI(){
        this.tweets =new Tweets();
    }

    @Test(enabled = true)
    public void testUserCanTweetSuccessfully(){
        // 1. user send a tweet
        String tweet="Hello, This is my first tweet from intelliJ with Rabah"+ UUID.randomUUID().toString();
        ValidatableResponse response= this.tweets.createTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    @Test(enabled = false)
    public void testUserCanNotTweetTheSameTweetTwiceInARow(){
        // 1. user send a tweet
        // String tweet="We are learning RestAPI Automation and Tweet check"+ UUID.randomUUID().toString();
        String tweet="Hello, This is my first tweet from intelliJ";
        ValidatableResponse response= this.tweets.createTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(200);

        System.out.println(response.extract().body().asString());
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
        // User send the same tweet again
        response= this.tweets.createTweet(tweet);
        // Verify that the tweet was unsuccessful
        response.statusCode(403);
        //System.out.println(response.extract().body().asString());
        String expectedMessage = "Status is a duplicate.";
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, expectedMessage);
        Assert.assertNotSame("200", 403);
    }

    @Test(enabled =false)
    public void testDelete(){
        String tweet="Hello, This is my first tweet from intelliJ with Rabah";
        ValidatableResponse response=this.tweets.deleteTweet(1307284386052739079l);
        // Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = false)
    public void testUserCanNotTweetTheSameTweetTwiceInARow1(){
        String tweet="Azul Felawen  "+ UUID.randomUUID().toString();
        ValidatableResponse response=this.tweets.createTweet(tweet);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
        response=this.tweets.createTweet(tweet);
        response.statusCode(403);
        String expectedMessage="Status is a duplicate.";
        String actualMessage=response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage,expectedMessage);
    }

    @Test(enabled = false)
    public void getMyUserTimeTweetTest(){

        ValidatableResponse response=this.tweets.getMyUserTimeTweet(1307288019985223692l);
        String actualMessage=response.extract().body().path("errors[0].message");
        response.statusCode(200);
        System.out.println(actualMessage);
    }


    @Test(enabled = false)
    public void testUserTimeTweetSuccessfully(){
        String tweet="This is a BootCamp Tweet";
        ValidatableResponse response= this.tweets.getUserTimeTweet(tweet);
        System.out.println(response);
    }



    @Test(enabled = false)
    public void testUserPlaceNearTweetSuccessfully(){
        String atUsername="@AribElhacen";
        ValidatableResponse response= this.tweets.getPlacesNear(atUsername);
        System.out.println(response);
    }


    @Test (enabled = false)
    public void testUserCanTweetAnImageSuccessfully(){


        String tweet="../Users/elhacenarib/Desktop/image.png";
        ValidatableResponse response= this.tweets.createImageTweet(tweet);

        response.statusCode(200);
        String actualTweet= response.extract().body().path("image");
        Assert.assertEquals(tweet,actualTweet);
    }




}