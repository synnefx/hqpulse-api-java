package com.hqpulse.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hqpulse.helper.utils.Utils;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ProtocolException;
import java.text.SimpleDateFormat;

/**
 *
 */
public class HQPulseClient {

    protected static String BASE_URL = "https://dev.cqms.synnefx.com/service/hmis/v1/";
    private static SimpleModule simpleModule = new SimpleModule();
    private static String version = "Unknown Version";
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        simpleModule.setDeserializerModifier(new BeanDeserializerModifier() {
            @Override
            public JsonDeserializer<?> modifyEnumDeserializer(DeserializationConfig config, JavaType type,
                                                              BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
                return new JsonDeserializer<Enum>() {
                    @Override
                    public Enum deserialize(JsonParser jp, DeserializationContext ctxt)
                            throws IOException, JsonProcessingException {
                        Class<? extends Enum> rawClass = (Class<Enum<?>>) type.getRawClass();
                        return Enum.valueOf(rawClass, jp.getValueAsString().toUpperCase().replace("-", "_"));
                    }
                };
            }
        });
        simpleModule.addSerializer(Enum.class, new StdSerializer<Enum>(Enum.class) {
            @Override
            public void serialize(Enum value, JsonGenerator gen, SerializerProvider provider)
                    throws IOException {
                gen.writeString(value.name().toLowerCase().replace("_", "-"));
            }
        });
    }

    private final Interceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(Level.BODY);
    private final String authId;
    private final String authToken;
    private boolean testing = false;
    private OkHttpClient httpClient;
    private Retrofit retrofit;
    private HQPulseAPIService apiService = null;

    {
        try {
            InputStream inputStream = HQPulseClient.class
                    .getResource("version.txt")
                    .openStream();

            version = new BufferedReader(new InputStreamReader(inputStream)).readLine();
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        //ISO 8601 Date format
        //objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'"));
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sssZ"));
        //objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }
    /**
     * Constructs a new HQpulseClient instance. To set a proxy, timeout etc, you can pass in an OkHttpClient.Builder, on which you can set
     * the timeout and proxy using:
     *
     * <pre><code>
     *   new OkHttpClient.Builder()
     *   .proxy(proxy)
     *   .connectTimeout(1, TimeUnit.MINUTES);
     * </code></pre>
     *
     * @param authId
     * @param authToken
     * @param httpClientBuilder
     * @param baseUrl
     * @param simpleModule
     */
    public HQPulseClient(String authId, String authToken, OkHttpClient.Builder httpClientBuilder, final String baseUrl, final SimpleModule simpleModule) {
        if (!(Utils.isAccountIdValid(authId))) {
            throw new IllegalArgumentException("invalid account ID");
        }

        this.authId = authId;
        this.authToken = authToken;
        this.objectMapper.registerModule(simpleModule);

        // HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        // loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient = httpClientBuilder

                .addNetworkInterceptor(loggingInterceptor)
                //.addInterceptor(loggingInterceptor)
                .addInterceptor(chain -> chain.proceed(
                        chain.request()
                                .newBuilder()
                                //.addHeader("Authorization", Credentials.basic(getAuthId(), getAuthToken()))
                                .addHeader("Authorization", String.format("Bearer %s", getAuthToken()))
                                .addHeader("User-Agent", String.format("%s/%s (Implementation: %s %s %s, Specification: %s %s %s)", "hqpulse-java", version,
                                        Runtime.class.getPackage().getImplementationVendor(),
                                        Runtime.class.getPackage().getImplementationTitle(),
                                        Runtime.class.getPackage().getImplementationVersion(),
                                        Runtime.class.getPackage().getSpecificationVendor(),
                                        Runtime.class.getPackage().getSpecificationTitle(),
                                        Runtime.class.getPackage().getSpecificationVersion()
                                ))
                                .build()
                ))
                .addNetworkInterceptor(chain -> {
                    Response response;
                    try {
                        response = chain.proceed(chain.request());
                    } catch (ProtocolException protocolException) {
                        // We return bodies for HTTP 204!
                        response = new Response.Builder()
                                .request(chain.request())
                                .code(204)
                                .protocol(Protocol.HTTP_1_1)
                                .body(ResponseBody.create(null, new byte[]{}))
                                .build();
                    }
                    return response;
                }).build();

        retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        this.apiService = retrofit.create(HQPulseAPIService.class);
    }
    /**
     * Constructs a new HQpulseClient instance. To set a proxy, timeout etc, you can pass in an OkHttpClient.Builder, on which you can set
     * the timeout and proxy using:
     *
     * <pre><code>
     *   new OkHttpClient.Builder()
     *   .proxy(proxy)
     *   .connectTimeout(1, TimeUnit.MINUTES);
     * </code></pre>
     *
     * @param authId
     * @param authToken
     */
    public HQPulseClient(String authId, String authToken) {
        this(authId, authToken, new OkHttpClient.Builder(), BASE_URL, simpleModule);
    }

    /**
     * Constructs a new HQpulseClient instance. To set a proxy, timeout etc, you can pass in an OkHttpClient.Builder, on which you can set
     * the timeout and proxy using:
     *
     * <pre><code>
     *   new OkHttpClient.Builder()
     *   .proxy(proxy)
     *   .connectTimeout(1, TimeUnit.MINUTES);
     * </code></pre>
     *
     * @param authId
     * @param authToken
     * @param httpClientBuilder
     */
    public HQPulseClient(String authId, String authToken, OkHttpClient.Builder httpClientBuilder) {
        this(authId, authToken, httpClientBuilder, BASE_URL, simpleModule);
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public boolean isTesting() {
        return testing;
    }

    public void setTesting(boolean testing) {
        this.testing = testing;
    }

    Retrofit getRetrofit() {
        return retrofit;
    }

    public HQPulseAPIService getApiService() {
        return apiService;
    }

    void setApiService(HQPulseAPIService apiService) {
        this.apiService = apiService;
    }

    public String getAuthId() {
        return authId;
    }

    public String getAuthToken() {
        return authToken;
    }
}
