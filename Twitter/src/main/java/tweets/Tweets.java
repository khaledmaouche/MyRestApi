package tweets;

import base.RestApi;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class Tweets extends RestApi {

    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";

    private final String DELETE_TWEET_ENDPOINT = "/statuses/destroy.json";

    //https://developer.twitter.com/en/docs/twitter-api/v1/tweets/timelines/api-reference/get-statuses-user_timeline
    private final String GET_USER_TWEET_ENDPOINT = "/statuses/user_timeline.json";
    private final String GET_PLACES_NEAR_ENDPOINT = "/geo/reverse_geocode.json";

    private final String SEARCH_TWEETS_ENDPOINT = "/search/tweets.json";
    private final String UPLOAD_USER_TWEET_ENDPOINT = "/media/upload.json";
    // /statuses/user_timeline.json

    // GET ALL Tweet Information
    public ValidatableResponse getUserTimeTweet(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .then();
    }

    // Create a Tweet from user twitter
    public ValidatableResponse createTweet(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when().post(this.baseUrl + this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    // Delete a tweet from users twitter
    public ValidatableResponse deleteTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.DELETE_TWEET_ENDPOINT)
                .then();
    }

    // Create a List of tweets from user's twitter with EXCEL SHEETgit
    public ValidatableResponse createListTweets(String Flights) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", Flights)
                .when().post(this.baseUrl + this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    public ValidatableResponse getMyUserTimeTweet(long id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("in_reply_to_status_id", id)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .then();

    }



    //api.openweathermap.org/data/2.5/weather? q=Bejaia & appid=1ad72609f6762e806b1c3a3398b5d82c
    private final String base = "api.openweathermap.org/data/2.5/weather";
    private final String q = "?Bejaia&";
    private final String appid = "1ad72609f6762e806b1c3a3398b5d82c";


    // GET Weather Information
    public void weather() {
        given().param(q + appid)
                .when().get(base)
                .then().statusCode(403);
    }

    //pro.openweathermap.org/data/2.5/forecast/hourly?q=Columbus,us&mode=xml&appid={84179274d8804ef8814ba6f7559c89b1}
    private final String base1 = "pro.openweathermap.org/data/2.5/forecast/hourly?";
    private final String q1 = "Columbus,us";
    private final String mode = "xml&";
    private final String appid1 = "{84179274d8804ef8814ba6f7559c89b1}";

    public void forecast() {
        given().param(q + appid1)
                .when().get(base1)
                .then().statusCode(200);
    }

    public ValidatableResponse createImageTweet(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("category", tweet)
                .when().post(this.baseUrl1+this.UPLOAD_USER_TWEET_ENDPOINT)
                .then();
    }

    // Delete a tweet
    public ValidatableResponse searchTweets (String atUsername){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("q", atUsername)
                .when().get(this.baseUrl + this.SEARCH_TWEETS_ENDPOINT).then();


    }

    public ValidatableResponse getPlacesNear (String atUsername){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("q", atUsername)
                .when().get(this.baseUrl + this.GET_PLACES_NEAR_ENDPOINT)
                .then();
    }


}
