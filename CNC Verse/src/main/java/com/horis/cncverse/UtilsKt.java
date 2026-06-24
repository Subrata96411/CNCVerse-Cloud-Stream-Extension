package com.horis.cncverse;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.ExtensionsKt;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import java.io.Closeable;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: Utils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\u001a\"\u0010\b\u001a\u0002H\t\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086\b¢\u0006\u0002\u0010\r\u001a$\u0010\u000e\u001a\u0004\u0018\u0001H\t\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086\b¢\u0006\u0002\u0010\r\u001a\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f\u001a\u0016\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\u0014\u001a\u000e\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f\u001a\u000e\u0010 \u001a\u00020\fH\u0086@¢\u0006\u0002\u0010!\u001a0\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00162\u0006\u0010#\u001a\u00020\f2\u0014\b\u0002\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0016\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u000e\u0010\u001f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"JSONParser", "Lcom/lagradost/nicehttp/ResponseParser;", "getJSONParser", "()Lcom/lagradost/nicehttp/ResponseParser;", "app", "Lcom/lagradost/nicehttp/Requests;", "getApp", "()Lcom/lagradost/nicehttp/Requests;", "parseJson", "T", "", "text", "", "(Ljava/lang/String;)Ljava/lang/Object;", "tryParseJson", "convertRuntimeToMinutes", "", "runtime", "bypass", "mainUrl", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newTvBaseHeaders", "", "getNewTvBaseHeaders", "()Ljava/util/Map;", "newTvDomains", "", "getNewTvDomains", "()Ljava/util/List;", "decodeBase64", "value", "resolvedApiUrl", "resolveApiUrl", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildNewTvHeaders", "ott", "extra", "CNC Verse_debug"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Utils.kt\ncom/horis/cncverse/UtilsKt\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 NiceResponse.kt\ncom/lagradost/nicehttp/NiceResponse\n*L\n1#1,394:1\n221#2,2:395\n221#2,2:400\n296#3,2:397\n62#4:399\n*S KotlinDebug\n*F\n+ 1 Utils.kt\ncom/horis/cncverse/UtilsKt\n*L\n291#1:395,2\n380#1:400,2\n298#1:397,2\n364#1:399\n*E\n"})
public final class UtilsKt {

    @NotNull
    private static final ResponseParser JSONParser = new ResponseParser() { // from class: com.horis.cncverse.UtilsKt$JSONParser$1
        private final ObjectMapper mapper = ExtensionsKt.jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);

        public final ObjectMapper getMapper() {
            return this.mapper;
        }

        public <T> T parse(String text, KClass<T> kClass) {
            return (T) this.mapper.readValue(text, JvmClassMappingKt.getJavaClass(kClass));
        }

        public <T> T parseSafe(String text, KClass<T> kClass) {
            try {
                return (T) this.mapper.readValue(text, JvmClassMappingKt.getJavaClass(kClass));
            } catch (Exception e) {
                return null;
            }
        }

        public String writeValueAsString(Object obj) {
            return this.mapper.writeValueAsString(obj);
        }
    };

    @NotNull
    private static final Requests app;

    @NotNull
    private static final Map<String, String> newTvBaseHeaders;

    @NotNull
    private static final List<String> newTvDomains;

    @NotNull
    private static String resolvedApiUrl;

    /* JADX INFO: renamed from: com.horis.cncverse.UtilsKt$resolveApiUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Utils.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.horis.cncverse.UtilsKt", f = "Utils.kt", i = {0, 0}, l = {363}, m = "resolveApiUrl", n = {"encoded", "base"}, nl = {364}, s = {"L$1", "L$2"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return UtilsKt.resolveApiUrl((Continuation) this);
        }
    }

    static {
        Requests $this$app_u24lambda_u240 = new Requests((OkHttpClient) null, (Map) null, (String) null, (Map) null, (Map) null, 0, (TimeUnit) null, 0L, JSONParser, 255, (DefaultConstructorMarker) null);
        $this$app_u24lambda_u240.setDefaultHeaders(MapsKt.mapOf(TuplesKt.to("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")));
        app = $this$app_u24lambda_u240;
        newTvBaseHeaders = MapsKt.mapOf(new Pair[]{TuplesKt.to("Cache-Control", "no-cache, no-store, must-revalidate"), TuplesKt.to("Pragma", "no-cache"), TuplesKt.to("Expires", "0"), TuplesKt.to("X-Requested-With", "NetmirrorNewTV v1.0"), TuplesKt.to("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:136.0) Gecko/20100101 Firefox/136.0 /OS.GatuNewTV v1.0"), TuplesKt.to("Accept", "application/json, text/plain, */*")});
        newTvDomains = CollectionsKt.listOf(new String[]{"aHR0cHM6Ly9tb2JpbGVkZXRlY3RzLmNvbQ==", "aHR0cHM6Ly9tb2JpbGVkZXRlY3QuYXBw", "aHR0cHM6Ly9tb2JpZGV0ZWN0LmFydA==", "aHR0cHM6Ly9tb2JpZGV0ZWN0LmNj", "aHR0cHM6Ly9tb2JpZGV0ZWN0LmNsaWNr", "aHR0cHM6Ly9tb2JpZGV0ZWN0Lmluaw==", "aHR0cHM6Ly9tb2JpZGV0ZWN0LmxpdmU=", "aHR0cHM6Ly9tb2JpZGV0ZWN0LnBybw==", "aHR0cHM6Ly9tb2JpZGV0ZWN0LnNob3A=", "aHR0cHM6Ly9tb2JpZGV0ZWN0LnNpdGU=", "aHR0cHM6Ly9tb2JpZGV0ZWN0LnNwYWNl", "aHR0cHM6Ly9tb2JpZGV0ZWN0LnN0b3Jl", "aHR0cHM6Ly9tb2JpZGV0ZWN0LnZpcA==", "aHR0cHM6Ly9tb2JpZGV0ZWN0Lndpa2k=", "aHR0cHM6Ly9tb2JpZGV0ZWN0Lnh5eg==", "aHR0cHM6Ly9tb2JpZGV0ZWN0cy5hcnQ=", "aHR0cHM6Ly9tb2JpZGV0ZWN0cy5jYw==", "aHR0cHM6Ly9tb2JpZGV0ZWN0cy5pbmZv", "aHR0cHM6Ly9tb2JpZGV0ZWN0cy5pbms=", "aHR0cHM6Ly9tb2JpZGV0ZWN0cy5saXZl", "aHR0cHM6Ly9tb2JpZGV0ZWN0cy5wcm8=", "aHR0cHM6Ly9tb2JpZGV0ZWN0cy5zdG9yZQ==", "aHR0cHM6Ly9tb2JpZGV0ZWN0cy50b3A=", "aHR0cHM6Ly9tb2JpZGV0ZWN0cy54eXo="});
        resolvedApiUrl = "";
    }

    @NotNull
    public static final ResponseParser getJSONParser() {
        return JSONParser;
    }

    @NotNull
    public static final Requests getApp() {
        return app;
    }

    public static final /* synthetic */ <T> T parseJson(String str) {
        ResponseParser jSONParser = getJSONParser();
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) jSONParser.parse(str, Reflection.getOrCreateKotlinClass(Object.class));
    }

    public static final /* synthetic */ <T> T tryParseJson(String str) {
        try {
            ResponseParser jSONParser = getJSONParser();
            Intrinsics.reifiedOperationMarker(4, "T");
            return (T) jSONParser.parseSafe(str, Reflection.getOrCreateKotlinClass(Object.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final int convertRuntimeToMinutes(@NotNull String runtime) {
        int minutes;
        int totalMinutes = 0;
        List<String> parts = StringsKt.split$default(runtime, new String[]{" "}, false, 0, 6, (Object) null);
        for (String part : parts) {
            if (StringsKt.endsWith$default(part, "h", false, 2, (Object) null)) {
                Integer intOrNull = StringsKt.toIntOrNull(StringsKt.trim(StringsKt.removeSuffix(part, "h")).toString());
                minutes = intOrNull != null ? intOrNull.intValue() : 0;
                totalMinutes += minutes * 60;
            } else if (StringsKt.endsWith$default(part, "m", false, 2, (Object) null)) {
                Integer intOrNull2 = StringsKt.toIntOrNull(StringsKt.trim(StringsKt.removeSuffix(part, "m")).toString());
                minutes = intOrNull2 != null ? intOrNull2.intValue() : 0;
                totalMinutes += minutes;
            }
        }
        return totalMinutes;
    }

    @Nullable
    public static final Object bypass(@NotNull String mainUrl, @NotNull Continuation<? super String> continuation) throws Exception {
        OkHttpClient client;
        Request.Builder $this$bypass_u24lambda_u240;
        Throwable th;
        Object element$iv;
        String strSubstringAfter$default;
        Pair<String, Long> cookie = NetflixMirrorStorage.INSTANCE.getCookie();
        String savedCookie = (String) cookie.component1();
        long savedTimestamp = ((Number) cookie.component2()).longValue();
        String str = savedCookie;
        if (!(str == null || str.length() == 0) && System.currentTimeMillis() - savedTimestamp < 54000000) {
            return savedCookie;
        }
        try {
            Map headers = MapsKt.mapOf(new Pair[]{TuplesKt.to("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"), TuplesKt.to("Accept-Encoding", "gzip, deflate, br, zstd"), TuplesKt.to("Accept-Language", "en-US,en;q=0.9"), TuplesKt.to("Cache-Control", "max-age=0"), TuplesKt.to("Connection", "keep-alive"), TuplesKt.to("Content-Type", "application/x-www-form-urlencoded"), TuplesKt.to("Origin", "https://net22.cc"), TuplesKt.to("Referer", "https://net22.cc/verify2"), TuplesKt.to("sec-ch-ua", "\"Google Chrome\";v=\"147\", \"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"147\""), TuplesKt.to("sec-ch-ua-mobile", "?0"), TuplesKt.to("sec-ch-ua-platform", "\"Windows\""), TuplesKt.to("Sec-Fetch-Dest", "document"), TuplesKt.to("Sec-Fetch-Mode", "navigate"), TuplesKt.to("Sec-Fetch-Site", "same-origin"), TuplesKt.to("Sec-Fetch-User", "?1"), TuplesKt.to("Upgrade-Insecure-Requests", "1"), TuplesKt.to("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/147.0.0.0 Safari/537.36")});
            RequestBody requestBodyBuild = new FormBody.Builder((Charset) null, 1, (DefaultConstructorMarker) null).add("g-recaptcha-response", UUID.randomUUID().toString()).build();
            client = app.getBaseClient().newBuilder().followRedirects(false).followSslRedirects(false).build();
            $this$bypass_u24lambda_u240 = new Request.Builder().url("https://net52.cc/verify.php").post(requestBodyBuild);
            for (Map.Entry element$iv2 : headers.entrySet()) {
                try {
                    String key = (String) element$iv2.getKey();
                    String value = (String) element$iv2.getValue();
                    $this$bypass_u24lambda_u240.addHeader(key, value);
                } catch (Exception e) {
                    e = e;
                    NetflixMirrorStorage.INSTANCE.clearCookie();
                    throw e;
                }
            }
        } catch (Exception e2) {
            e = e2;
        }
        try {
            Request request = $this$bypass_u24lambda_u240.build();
            Response response = (Closeable) client.newCall(request).execute();
            try {
                Response response2 = response;
                Iterable $this$firstOrNull$iv = response2.headers("Set-Cookie");
                Iterator it = $this$firstOrNull$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        element$iv = null;
                        break;
                    }
                    element$iv = it.next();
                    String it2 = (String) element$iv;
                    Response response3 = response2;
                    String savedCookie2 = savedCookie;
                    long savedTimestamp2 = savedTimestamp;
                    try {
                        if (StringsKt.startsWith$default(it2, "t_hash_t=", false, 2, (Object) null)) {
                            break;
                        }
                        savedCookie = savedCookie2;
                        response2 = response3;
                        savedTimestamp = savedTimestamp2;
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            throw th;
                        } catch (Throwable th3) {
                            CloseableKt.closeFinally(response, th);
                            throw th3;
                        }
                    }
                }
                String str2 = (String) element$iv;
                String newCookie = (str2 == null || (strSubstringAfter$default = StringsKt.substringAfter$default(str2, "t_hash_t=", (String) null, 2, (Object) null)) == null) ? null : StringsKt.substringBefore$default(strSubstringAfter$default, ";", (String) null, 2, (Object) null);
                if (newCookie == null) {
                    newCookie = "";
                }
                CloseableKt.closeFinally(response, (Throwable) null);
                if (newCookie.length() > 0) {
                    NetflixMirrorStorage.INSTANCE.saveCookie(newCookie);
                }
                return newCookie;
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Exception e3) {
            e = e3;
            NetflixMirrorStorage.INSTANCE.clearCookie();
            throw e;
        }
    }

    @NotNull
    public static final Map<String, String> getNewTvBaseHeaders() {
        return newTvBaseHeaders;
    }

    @NotNull
    public static final List<String> getNewTvDomains() {
        return newTvDomains;
    }

    @NotNull
    public static final String decodeBase64(@NotNull String value) {
        return new String(Base64.getDecoder().decode(value), Charsets.UTF_8);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:24|(1:63)|25|26|61|27|28|67|29|(1:31)(5:32|59|33|(1:39)(1:38)|(5:41|69|42|43|44)(3:47|22|(2:57|58)(0)))) */
    /* JADX WARN: Can't wrap try/catch for region: R(10:24|63|25|26|61|27|28|67|29|(1:31)(5:32|59|33|(1:39)(1:38)|(5:41|69|42|43|44)(3:47|22|(2:57|58)(0)))) */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0135, code lost:
    
        r10 = r2;
        r2 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x013e, code lost:
    
        r10 = r8;
        r2 = r2;
     */
    /* JADX WARN: Path cross not found for [B:35:0x0106, B:39:0x010f], limit reached: 70 */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0112 A[Catch: Exception -> 0x012e, TRY_LEAVE, TryCatch #0 {Exception -> 0x012e, blocks: (B:33:0x00e1, B:35:0x0106, B:41:0x0112, B:43:0x011a), top: B:59:0x00e1 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x00d9 -> B:59:0x00e1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x0135 -> B:56:0x0158). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x013e -> B:56:0x0158). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x014c -> B:56:0x0158). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object resolveApiUrl(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r27) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 366
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horis.cncverse.UtilsKt.resolveApiUrl(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Map buildNewTvHeaders$default(String str, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            map = MapsKt.emptyMap();
        }
        return buildNewTvHeaders(str, map);
    }

    @NotNull
    public static final Map<String, String> buildNewTvHeaders(@NotNull String ott, @NotNull Map<String, String> map) {
        Map<String, String> mutableMap = MapsKt.toMutableMap(newTvBaseHeaders);
        mutableMap.put("Ott", ott);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            mutableMap.put(key, value);
        }
        return mutableMap;
    }
}
