package com.cncverse;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagradost.cloudstream3.LiveSearchResponse;
import com.lagradost.cloudstream3.LiveStreamLoadResponse;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DrmExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import java.io.Closeable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.text.CharsKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: SKTech.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nSKTech.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SKTech.kt\ncom/cncverse/SKTech\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 AppUtils.kt\ncom/lagradost/cloudstream3/utils/AppUtils\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n+ 6 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 7 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,1322:1\n1586#2:1323\n1661#2,3:1324\n296#2,2:1346\n1512#2:1348\n1538#2,3:1349\n1541#2,3:1359\n1586#2:1365\n1661#2,3:1366\n777#2:1370\n873#2,2:1371\n1586#2:1373\n1661#2,3:1374\n1586#2:1415\n1661#2,3:1416\n63#3:1327\n64#3,15:1329\n63#3:1377\n64#3,15:1379\n63#3:1396\n64#3,15:1398\n1#4:1328\n1#4:1378\n1#4:1397\n1#4:1419\n50#5:1344\n43#5:1345\n50#5:1394\n43#5:1395\n50#5:1413\n43#5:1414\n383#6,7:1352\n129#7:1362\n158#7,2:1363\n160#7:1369\n*S KotlinDebug\n*F\n+ 1 SKTech.kt\ncom/cncverse/SKTech\n*L\n135#1:1323\n135#1:1324,3\n225#1:1346,2\n243#1:1348\n243#1:1349,3\n243#1:1359,3\n245#1:1365\n245#1:1366,3\n276#1:1370\n276#1:1371,2\n276#1:1373\n276#1:1374,3\n640#1:1415\n640#1:1416,3\n222#1:1327\n222#1:1329,15\n297#1:1377\n297#1:1379,15\n566#1:1396\n566#1:1398,15\n222#1:1328\n297#1:1378\n566#1:1397\n222#1:1344\n222#1:1345\n297#1:1394\n297#1:1395\n566#1:1413\n566#1:1414\n243#1:1352,7\n243#1:1362\n243#1:1363,2\n243#1:1369\n*E\n"})
public final class SKTech extends MainAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    public static final String EXT_INF = "#EXTINF";

    @NotNull
    public static final String EXT_M3U = "#EXTM3U";

    @NotNull
    public static final String EXT_VLC_OPT = "#EXTVLCOPT";

    @NotNull

    @Nullable
    private static Context context;

    /* JADX INFO: renamed from: customHttpClient$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy customHttpClient;

    @NotNull
    private final String customMainUrl;

    @NotNull
    private final String customName;
    private final boolean hasChromecastSupport;
    private final boolean hasMainPage;

    @NotNull
    private final Map<String, String> headers;

    @NotNull
    private String lang;

    @NotNull
    private String mainUrl;

    @NotNull
    private String name;

    @NotNull
    private final Set<TvType> supportedTypes;

    /* JADX INFO: renamed from: com.cncverse.SKTech$getMainPage$1, reason: invalid class name */
    /* JADX INFO: compiled from: SKTech.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.SKTech", f = "SKTech.kt", i = {0, 0}, l = {240}, m = "getMainPage", n = {"request", "page"}, nl = {241}, s = {"L$0", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SKTech.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.SKTech$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SKTech.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.SKTech", f = "SKTech.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6}, l = {612, 651, 671, 689, 715, 738, 762}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "loadData", "headers", "normalizedKey", "normalizedKid", "playerKey", "playerKid", "isCasting", "hasValidKeys", "hasLicenseUrl", "data", "subtitleCallback", "callback", "loadData", "headers", "mpdStr", "regex", "matchResult", "drmKid", "drmKidBytes", "drmKidBase64", "keyBase64", "isCasting", "hasValidKeys", "hasLicenseUrl", "data", "subtitleCallback", "callback", "loadData", "headers", "mpdStr", "regex", "matchResult", "drmKid", "drmKidBytes", "drmKidBase64", "keyBase64", "isCasting", "hasValidKeys", "hasLicenseUrl", "data", "subtitleCallback", "callback", "loadData", "headers", "isCasting", "hasValidKeys", "hasLicenseUrl", "data", "subtitleCallback", "callback", "loadData", "headers", "isCasting", "data", "subtitleCallback", "callback", "loadData", "headers", "isCasting", "data", "subtitleCallback", "callback", "loadData", "headers", "isCasting"}, nl = {611, 650, 670, 688, 714, 737, 761}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "Z$0"}, v = 2)
    static final class C00151 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C00151(Continuation<? super C00151> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SKTech.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.SKTech$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SKTech.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.SKTech", f = "SKTech.kt", i = {0}, l = {273}, m = "search", n = {"query"}, nl = {274}, s = {"L$0"}, v = 2)
    static final class C00161 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00161(Continuation<? super C00161> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SKTech.this.search(null, (Continuation) this);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SKTech() {
        String str = null;
        this(str, str, 3, str);
    }

    public SKTech(@NotNull String customName, @NotNull String customMainUrl) {
        this.customName = customName;
        this.customMainUrl = customMainUrl;
        this.lang = "ta";
        this.mainUrl = this.customMainUrl;
        this.name = this.customName;
        this.hasMainPage = true;
        this.hasChromecastSupport = true;
        this.supportedTypes = SetsKt.setOf(TvType.Live);
        this.headers = MapsKt.mapOf(new Pair[]{TuplesKt.to("accept", "*/*"), TuplesKt.to("Cache-Control", "no-cache, no-store"), TuplesKt.to("User-Agent", "Mozilla/5.0 (Windows NT 10.0; rv:78.0) Gecko/20100101 Firefox/78.0")});
        this.customHttpClient = LazyKt.lazy(new Function0() { // from class: com.cncverse.SKTech$$ExternalSyntheticLambda10
            public final Object invoke() {
                return SKTech.customHttpClient_delegate$lambda$0(this.f$0);
            }
        });
    }

    public /* synthetic */ SKTech(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "IPTV Player" : str, (i & 2) != 0 ? "https://fifabd.site/OPLLX7/LIVE2.m3u" : str2);
    }

    /* JADX INFO: compiled from: SKTech.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/cncverse/SKTech$Companion;", "", "<init>", "()V", "OMG10", "", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "EXT_M3U", "EXT_INF", "EXT_VLC_OPT", "SKTechProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return SKTech.context;
        }

        public final void setContext(@Nullable Context context) {
            SKTech.context = context;
        }
    }

    @NotNull
    public String getLang() {
        return this.lang;
    }

    public void setLang(@NotNull String str) {
        this.lang = str;
    }

    @NotNull
    public String getMainUrl() {
        return this.mainUrl;
    }

    public void setMainUrl(@NotNull String str) {
        this.mainUrl = str;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public void setName(@NotNull String str) {
        this.name = str;
    }

    public boolean getHasMainPage() {
        return this.hasMainPage;
    }

    public boolean getHasChromecastSupport() {
        return this.hasChromecastSupport;
    }

    @NotNull
    public Set<TvType> getSupportedTypes() {
        return this.supportedTypes;
    }

    private final OkHttpClient getCustomHttpClient() {
        return (OkHttpClient) this.customHttpClient.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OkHttpClient customHttpClient_delegate$lambda$0(SKTech this$0) {
        return new OkHttpClient.Builder().addInterceptor(new HeaderReplacementInterceptor(this$0.headers)).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getWithCustomHeaders(String url, Continuation<? super String> continuation) {
        Request request = new Request.Builder().url(url).build();
        Response response = (Closeable) getCustomHttpClient().newCall(request).execute();
        try {
            Response response2 = response;
            String strString = response2.body().string();
            CloseableKt.closeFinally(response, (Throwable) null);
            return strString;
        } finally {
        }
    }

    private final String base64ToHexOrNull(String $this$base64ToHexOrNull) {
        String raw = StringsKt.trim($this$base64ToHexOrNull).toString();
        String normalizedHex = StringsKt.replace$default(raw, "-", "", false, 4, (Object) null);
        if ((normalizedHex.length() > 0) && normalizedHex.length() % 2 == 0) {
            if (new Regex("^[0-9a-fA-F]+$").matches(normalizedHex)) {
                String lowerCase = normalizedHex.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                return lowerCase;
            }
        }
        try {
            String value = StringsKt.replace$default(StringsKt.replace$default(raw, '-', '+', false, 4, (Object) null), '_', '/', false, 4, (Object) null);
            int padding = (4 - (value.length() % 4)) % 4;
            String normalized = value + StringsKt.repeat("=", padding);
            byte[] decoded = Base64.decode(normalized, 0);
            return ArraysKt.joinToString$default(decoded, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.cncverse.SKTech$$ExternalSyntheticLambda5
                public final Object invoke(Object obj) {
                    return SKTech.base64ToHexOrNull$lambda$1(((Byte) obj).byteValue());
                }
            }, 30, (Object) null);
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence base64ToHexOrNull$lambda$1(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final String hexToBase64UrlOrNull(String $this$hexToBase64UrlOrNull) {
        String normalizedHex = StringsKt.replace$default(StringsKt.trim($this$hexToBase64UrlOrNull).toString(), "-", "", false, 4, (Object) null);
        if (!(normalizedHex.length() == 0) && normalizedHex.length() % 2 == 0) {
            if (new Regex("^[0-9a-fA-F]+$").matches(normalizedHex)) {
                try {
                    Iterable $this$map$iv = StringsKt.chunked(normalizedHex, 2);
                    Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                    for (Object item$iv$iv : $this$map$iv) {
                        String it = (String) item$iv$iv;
                        destination$iv$iv.add(Byte.valueOf((byte) Integer.parseInt(it, CharsKt.checkRadix(16))));
                    }
                    byte[] bytes = CollectionsKt.toByteArray((List) destination$iv$iv);
                    return Base64.encodeToString(bytes, 11);
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }

    private final String decryptContent(String content) {
        try {
            if (!StringsKt.startsWith$default(content, EXT_M3U, false, 2, (Object) null) && !StringsKt.startsWith$default(content, EXT_INF, false, 2, (Object) null) && !StringsKt.startsWith$default(content, "#KODIPROP", false, 2, (Object) null)) {
                String trimmedContent = StringsKt.trim(content).toString();
                if (trimmedContent.length() < 79) {
                    return trimmedContent;
                }
                String part1 = trimmedContent.substring(0, 10);
                Intrinsics.checkNotNullExpressionValue(part1, "substring(...)");
                String part2 = trimmedContent.substring(34, trimmedContent.length() - 54);
                Intrinsics.checkNotNullExpressionValue(part2, "substring(...)");
                String part3 = trimmedContent.substring(trimmedContent.length() - 10);
                Intrinsics.checkNotNullExpressionValue(part3, "substring(...)");
                String encryptedData = part1 + part2 + part3;
                String ivBase64 = trimmedContent.substring(10, 34);
                Intrinsics.checkNotNullExpressionValue(ivBase64, "substring(...)");
                String keyBase64 = trimmedContent.substring(trimmedContent.length() - 54, trimmedContent.length() - 10);
                Intrinsics.checkNotNullExpressionValue(keyBase64, "substring(...)");
                byte[] iv = Base64.decode(ivBase64, 0);
                byte[] key = Base64.decode(keyBase64, 0);
                byte[] encrypted = Base64.decode(encryptedData, 0);
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
                IvParameterSpec ivSpec = new IvParameterSpec(iv);
                cipher.init(2, secretKey, ivSpec);
                byte[] decrypted = cipher.doFinal(encrypted);
                return new String(decrypted, StandardCharsets.UTF_8);
            }
            return content;
        } catch (Exception e) {
            return content;
        }
    }

    private final String getMpdStream(String url, Map<String, String> customHeaders) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new HeaderReplacementInterceptor(customHeaders)).build();
        Request request = new Request.Builder().url(url).build();
        Response response = (Closeable) client.newCall(request).execute();
        try {
            Response response2 = response;
            String strString = response2.body().string();
            CloseableKt.closeFinally(response, (Throwable) null);
            return strString;
        } finally {
        }
    }

    private final String getDRMKeysFromLicenseServer(String url, String kid) {
        Throwable th;
        Object obj;
        Object objDecodeFromString;
        List keys;
        Object element$iv;
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new HeaderReplacementInterceptor(MapsKt.mapOf(new Pair[]{TuplesKt.to("User-Agent", "Dalvik/2.1.0 (Linux; U; Android)"), TuplesKt.to("Content-Type", "application/json;charset=UTF-8")}))).addInterceptor(new LoggingInterceptor()).build();
        String json = "{\"kids\":[\"" + kid + "\"],\"type\":\"temporary\"}";
        MediaType mediaType = MediaType.Companion.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.Companion.create(json, mediaType);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = (Closeable) client.newCall(request).execute();
        try {
            Response response2 = response;
            String responseBody = response2.body().string();
            AppUtils appUtils = AppUtils.INSTANCE;
            try {
                Result.Companion companion = Result.Companion;
                try {
                    try {
                        KType kTypeTypeOf = Reflection.typeOf(Map.class, KTypeProjection.Companion.invariant(Reflection.typeOf(String.class)), KTypeProjection.Companion.invariant(Reflection.typeOf(Object.class)));
                        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
                        obj = Result.constructor-impl(SerializersKt.serializer(kTypeTypeOf));
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            Result.Companion companion2 = Result.Companion;
                            obj = Result.constructor-impl(ResultKt.createFailure(th));
                        } catch (Throwable th3) {
                            th = th3;
                            try {
                                throw th;
                            } catch (Throwable th4) {
                                CloseableKt.closeFinally(response, th);
                                throw th4;
                            }
                        }
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            } catch (Throwable th6) {
                th = th6;
            }
            if (Result.exceptionOrNull-impl(obj) != null) {
                try {
                    Result.Companion companion3 = Result.Companion;
                } catch (Throwable th7) {
                    th = th7;
                }
                try {
                    obj = Result.constructor-impl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Map.class), (List) null, 2, (Object) null));
                } catch (Throwable th8) {
                    th = th8;
                    try {
                        Result.Companion companion4 = Result.Companion;
                        obj = Result.constructor-impl(ResultKt.createFailure(th));
                    } catch (Throwable th9) {
                        th = th9;
                        throw th;
                    }
                }
            }
            if (Result.isFailure-impl(obj)) {
                obj = null;
            }
            DeserializationStrategy deserializationStrategy = (KSerializer) obj;
            if (deserializationStrategy != null) {
                try {
                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(deserializationStrategy, responseBody);
                } catch (SerializationException e) {
                    ArchComponentExtKt.logError(e);
                    ObjectMapper $this$readValue$iv$iv = MainAPIKt.getMapper();
                    objDecodeFromString = $this$readValue$iv$iv.readValue(responseBody, new TypeReference<Map<String, ? extends Object>>() { // from class: com.cncverse.SKTech$getDRMKeysFromLicenseServer$lambda$0$$inlined$parseJson$1
                    });
                } catch (Throwable th10) {
                    ObjectMapper $this$readValue$iv$iv2 = MainAPIKt.getMapper();
                    objDecodeFromString = $this$readValue$iv$iv2.readValue(responseBody, new TypeReference<Map<String, ? extends Object>>() { // from class: com.cncverse.SKTech$getDRMKeysFromLicenseServer$lambda$0$$inlined$parseJson$1
                    });
                }
            } else {
                ObjectMapper $this$readValue$iv$iv22 = MainAPIKt.getMapper();
                objDecodeFromString = $this$readValue$iv$iv22.readValue(responseBody, new TypeReference<Map<String, ? extends Object>>() { // from class: com.cncverse.SKTech$getDRMKeysFromLicenseServer$lambda$0$$inlined$parseJson$1
                });
            }
            Map jsonResponse = (Map) objDecodeFromString;
            Object obj2 = jsonResponse.get("keys");
            List keys2 = obj2 instanceof List ? (List) obj2 : null;
            if (keys2 != null) {
                Iterable $this$firstOrNull$iv = keys2;
                Iterator it = $this$firstOrNull$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        keys = keys2;
                        element$iv = null;
                        break;
                    }
                    element$iv = it.next();
                    Map it2 = (Map) element$iv;
                    Map jsonResponse2 = jsonResponse;
                    keys = keys2;
                    if (Intrinsics.areEqual(it2.get("kid"), kid)) {
                        break;
                    }
                    jsonResponse = jsonResponse2;
                    keys2 = keys;
                }
                Map matchedKey = (Map) element$iv;
                if (matchedKey != null || (matchedKey = (Map) CollectionsKt.firstOrNull(keys)) != null) {
                    String str = (String) matchedKey.get("k");
                    String str2 = str != null ? str : "";
                    CloseableKt.closeFinally(response, (Throwable) null);
                    return str2;
                }
            }
            CloseableKt.closeFinally(response, (Throwable) null);
            return "";
        } catch (Throwable th11) {
            th = th11;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getMainPage(int r47, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.MainPageRequest r48, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.HomePageResponse> r49) {
        /*
            Method dump skipped, instruction units count: 568
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.SKTech.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$1$0$0(String $posterurl, PlaylistItem $channel, LiveSearchResponse $this$newLiveSearchResponse) {
        $this$newLiveSearchResponse.setPosterUrl($posterurl);
        $this$newLiveSearchResponse.getApiName();
        $this$newLiveSearchResponse.setLang($channel.getAttributes().get("group-title"));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object search(@org.jetbrains.annotations.NotNull java.lang.String r34, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.SearchResponse>> r35) {
        /*
            Method dump skipped, instruction units count: 398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.SKTech.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit search$lambda$1$0(String $posterurl, PlaylistItem $channel, LiveSearchResponse $this$newLiveSearchResponse) {
        $this$newLiveSearchResponse.setPosterUrl($posterurl);
        $this$newLiveSearchResponse.getApiName();
        $this$newLiveSearchResponse.setLang($channel.getAttributes().get("group-title"));
        return Unit.INSTANCE;
    }

    @Nullable
    public Object load(@NotNull String url, @NotNull Continuation<? super LoadResponse> continuation) {
        Object obj;
        Object objDecodeFromString;
        AppUtils appUtils = AppUtils.INSTANCE;
        try {
            Result.Companion companion = Result.Companion;
            KType kTypeTypeOf = Reflection.typeOf(LoadData.class);
            MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
            obj = Result.constructor-impl(SerializersKt.serializer(kTypeTypeOf));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.exceptionOrNull-impl(obj) != null) {
            try {
                Result.Companion companion3 = Result.Companion;
                obj = Result.constructor-impl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(LoadData.class), (List) null, 2, (Object) null));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.Companion;
                obj = Result.constructor-impl(ResultKt.createFailure(th2));
            }
        }
        if (Result.isFailure-impl(obj)) {
            obj = null;
        }
        DeserializationStrategy deserializationStrategy = (KSerializer) obj;
        if (deserializationStrategy != null) {
            try {
                objDecodeFromString = MainAPIKt.getJson().decodeFromString(deserializationStrategy, url);
            } catch (SerializationException e) {
                ArchComponentExtKt.logError(e);
                ObjectMapper $this$readValue$iv$iv = MainAPIKt.getMapper();
                objDecodeFromString = $this$readValue$iv$iv.readValue(url, new TypeReference<LoadData>() { // from class: com.cncverse.SKTech$load$$inlined$parseJson$1
                });
            } catch (Throwable th3) {
                ObjectMapper $this$readValue$iv$iv2 = MainAPIKt.getMapper();
                objDecodeFromString = $this$readValue$iv$iv2.readValue(url, new TypeReference<LoadData>() { // from class: com.cncverse.SKTech$load$$inlined$parseJson$1
                });
            }
        } else {
            ObjectMapper $this$readValue$iv$iv22 = MainAPIKt.getMapper();
            objDecodeFromString = $this$readValue$iv$iv22.readValue(url, new TypeReference<LoadData>() { // from class: com.cncverse.SKTech$load$$inlined$parseJson$1
            });
        }
        LoadData data = (LoadData) objDecodeFromString;
        return MainAPIKt.newLiveStreamLoadResponse(this, data.getTitle(), url, url, new AnonymousClass2(data, null), continuation);
    }

    /* JADX INFO: renamed from: com.cncverse.SKTech$load$2, reason: invalid class name */
    /* JADX INFO: compiled from: SKTech.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/LiveStreamLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.SKTech$load$2", f = "SKTech.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<LiveStreamLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ LoadData $data;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(LoadData loadData, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$data = loadData;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = new AnonymousClass2(this.$data, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(LiveStreamLoadResponse liveStreamLoadResponse, Continuation<? super Unit> continuation) {
            return create(liveStreamLoadResponse, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            LiveStreamLoadResponse $this$newLiveStreamLoadResponse = (LiveStreamLoadResponse) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newLiveStreamLoadResponse.setPosterUrl(this.$data.getPoster());
                    $this$newLiveStreamLoadResponse.setPlot(this.$data.getNation());
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: compiled from: SKTech.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010$\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001By\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\r\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\r¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\u0015\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\rHÆ\u0003J\u0015\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\rHÆ\u0003J\u008f\u0001\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\r2\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\rHÆ\u0001J\u0014\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010-\u001a\u00020.HÖ\u0081\u0004J\n\u0010/\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\r¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001c¨\u00060"}, d2 = {"Lcom/cncverse/SKTech$LoadData;", "", "url", "", "title", "poster", "nation", "key", "keyid", "userAgent", "cookie", "licenseUrl", "drmKeys", "", "headers", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;)V", "getUrl", "()Ljava/lang/String;", "getTitle", "getPoster", "getNation", "getKey", "getKeyid", "getUserAgent", "getCookie", "getLicenseUrl", "getDrmKeys", "()Ljava/util/Map;", "getHeaders", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "hashCode", "", "toString", "SKTechProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class LoadData {

        @NotNull
        private final String cookie;

        @NotNull
        private final Map<String, String> drmKeys;

        @NotNull
        private final Map<String, String> headers;

        @NotNull
        private final String key;

        @NotNull
        private final String keyid;

        @NotNull
        private final String licenseUrl;

        @NotNull
        private final String nation;

        @NotNull
        private final String poster;

        @NotNull
        private final String title;

        @NotNull
        private final String url;

        @NotNull
        private final String userAgent;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ LoadData copy$default(LoadData loadData, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Map map, Map map2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = loadData.url;
            }
            if ((i & 2) != 0) {
                str2 = loadData.title;
            }
            if ((i & 4) != 0) {
                str3 = loadData.poster;
            }
            if ((i & 8) != 0) {
                str4 = loadData.nation;
            }
            if ((i & 16) != 0) {
                str5 = loadData.key;
            }
            if ((i & 32) != 0) {
                str6 = loadData.keyid;
            }
            if ((i & 64) != 0) {
                str7 = loadData.userAgent;
            }
            if ((i & 128) != 0) {
                str8 = loadData.cookie;
            }
            if ((i & 256) != 0) {
                str9 = loadData.licenseUrl;
            }
            if ((i & 512) != 0) {
                map = loadData.drmKeys;
            }
            if ((i & 1024) != 0) {
                map2 = loadData.headers;
            }
            Map map3 = map;
            Map map4 = map2;
            String str10 = str8;
            String str11 = str9;
            String str12 = str6;
            String str13 = str7;
            String str14 = str5;
            String str15 = str3;
            return loadData.copy(str, str2, str15, str4, str14, str12, str13, str10, str11, map3, map4);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @NotNull
        public final Map<String, String> component10() {
            return this.drmKeys;
        }

        @NotNull
        public final Map<String, String> component11() {
            return this.headers;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getPoster() {
            return this.poster;
        }

        @NotNull
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getNation() {
            return this.nation;
        }

        @NotNull
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getKey() {
            return this.key;
        }

        @NotNull
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getKeyid() {
            return this.keyid;
        }

        @NotNull
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getUserAgent() {
            return this.userAgent;
        }

        @NotNull
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getCookie() {
            return this.cookie;
        }

        @NotNull
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getLicenseUrl() {
            return this.licenseUrl;
        }

        @NotNull
        public final LoadData copy(@NotNull String url, @NotNull String title, @NotNull String poster, @NotNull String nation, @NotNull String key, @NotNull String keyid, @NotNull String userAgent, @NotNull String cookie, @NotNull String licenseUrl, @NotNull Map<String, String> drmKeys, @NotNull Map<String, String> headers) {
            return new LoadData(url, title, poster, nation, key, keyid, userAgent, cookie, licenseUrl, drmKeys, headers);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LoadData)) {
                return false;
            }
            LoadData loadData = (LoadData) other;
            return Intrinsics.areEqual(this.url, loadData.url) && Intrinsics.areEqual(this.title, loadData.title) && Intrinsics.areEqual(this.poster, loadData.poster) && Intrinsics.areEqual(this.nation, loadData.nation) && Intrinsics.areEqual(this.key, loadData.key) && Intrinsics.areEqual(this.keyid, loadData.keyid) && Intrinsics.areEqual(this.userAgent, loadData.userAgent) && Intrinsics.areEqual(this.cookie, loadData.cookie) && Intrinsics.areEqual(this.licenseUrl, loadData.licenseUrl) && Intrinsics.areEqual(this.drmKeys, loadData.drmKeys) && Intrinsics.areEqual(this.headers, loadData.headers);
        }

        public int hashCode() {
            return (((((((((((((((((((this.url.hashCode() * 31) + this.title.hashCode()) * 31) + this.poster.hashCode()) * 31) + this.nation.hashCode()) * 31) + this.key.hashCode()) * 31) + this.keyid.hashCode()) * 31) + this.userAgent.hashCode()) * 31) + this.cookie.hashCode()) * 31) + this.licenseUrl.hashCode()) * 31) + this.drmKeys.hashCode()) * 31) + this.headers.hashCode();
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("LoadData(url=").append(this.url).append(", title=").append(this.title).append(", poster=").append(this.poster).append(", nation=").append(this.nation).append(", key=").append(this.key).append(", keyid=").append(this.keyid).append(", userAgent=").append(this.userAgent).append(", cookie=").append(this.cookie).append(", licenseUrl=").append(this.licenseUrl).append(", drmKeys=").append(this.drmKeys).append(", headers=").append(this.headers).append(')');
            return sb.toString();
        }

        public LoadData(@NotNull String url, @NotNull String title, @NotNull String poster, @NotNull String nation, @NotNull String key, @NotNull String keyid, @NotNull String userAgent, @NotNull String cookie, @NotNull String licenseUrl, @NotNull Map<String, String> map, @NotNull Map<String, String> map2) {
            this.url = url;
            this.title = title;
            this.poster = poster;
            this.nation = nation;
            this.key = key;
            this.keyid = keyid;
            this.userAgent = userAgent;
            this.cookie = cookie;
            this.licenseUrl = licenseUrl;
            this.drmKeys = map;
            this.headers = map2;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ LoadData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Map map, Map map2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            Map mapEmptyMap;
            if ((i & 512) == 0) {
                mapEmptyMap = map;
            } else {
                mapEmptyMap = MapsKt.emptyMap();
            }
            this(str, str2, str3, str4, str5, str6, str7, str8, str9, mapEmptyMap, map2);
        }

        @NotNull
        public final String getUrl() {
            return this.url;
        }

        @NotNull
        public final String getTitle() {
            return this.title;
        }

        @NotNull
        public final String getPoster() {
            return this.poster;
        }

        @NotNull
        public final String getNation() {
            return this.nation;
        }

        @NotNull
        public final String getKey() {
            return this.key;
        }

        @NotNull
        public final String getKeyid() {
            return this.keyid;
        }

        @NotNull
        public final String getUserAgent() {
            return this.userAgent;
        }

        @NotNull
        public final String getCookie() {
            return this.cookie;
        }

        @NotNull
        public final String getLicenseUrl() {
            return this.licenseUrl;
        }

        @NotNull
        public final Map<String, String> getDrmKeys() {
            return this.drmKeys;
        }

        @NotNull
        public final Map<String, String> getHeaders() {
            return this.headers;
        }
    }


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX WARN: Removed duplicated region for block: B:101:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0374  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x044b  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x04cf A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x04d0  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x04e7  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x074c  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r31, boolean r32, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r33, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r34, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r35) {
        /*
            Method dump skipped, instruction units count: 2366
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.SKTech.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadLinks$lambda$0$0(Context $_ctx) {
        Toast.makeText($_ctx, "⚠️(Opening ads) Subscription expired. If you have renewed your subscription, please re-verify it in Subscription Manager.", 1).show();
    }

    /* JADX INFO: renamed from: com.cncverse.SKTech$loadLinks$3, reason: invalid class name */
    /* JADX INFO: compiled from: SKTech.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/DrmExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.SKTech$loadLinks$3", f = "SKTech.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<DrmExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ Map<String, String> $headers;
        final /* synthetic */ String $playerKey;
        final /* synthetic */ String $playerKid;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Map<String, String> map, String str, String str2, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$headers = map;
            this.$playerKey = str;
            this.$playerKid = str2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass3 = new AnonymousClass3(this.$headers, this.$playerKey, this.$playerKid, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        public final Object invoke(DrmExtractorLink drmExtractorLink, Continuation<? super Unit> continuation) {
            return create(drmExtractorLink, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            DrmExtractorLink $this$newDrmExtractorLink = (DrmExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newDrmExtractorLink.setQuality(Qualities.Unknown.getValue());
                    if (!this.$headers.isEmpty()) {
                        $this$newDrmExtractorLink.setHeaders(this.$headers);
                    }
                    $this$newDrmExtractorLink.setKey(this.$playerKey);
                    $this$newDrmExtractorLink.setKid(this.$playerKid);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.SKTech$loadLinks$4, reason: invalid class name */
    /* JADX INFO: compiled from: SKTech.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/DrmExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.SKTech$loadLinks$4", f = "SKTech.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<DrmExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $drmKidBase64;
        final /* synthetic */ Map<String, String> $headers;
        final /* synthetic */ String $keyBase64;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(Map<String, String> map, String str, String str2, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$headers = map;
            this.$keyBase64 = str;
            this.$drmKidBase64 = str2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass4 = new AnonymousClass4(this.$headers, this.$keyBase64, this.$drmKidBase64, continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        public final Object invoke(DrmExtractorLink drmExtractorLink, Continuation<? super Unit> continuation) {
            return create(drmExtractorLink, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            DrmExtractorLink $this$newDrmExtractorLink = (DrmExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newDrmExtractorLink.setQuality(Qualities.Unknown.getValue());
                    if (!this.$headers.isEmpty()) {
                        $this$newDrmExtractorLink.setHeaders(this.$headers);
                    }
                    $this$newDrmExtractorLink.setKey(StringsKt.trim(this.$keyBase64).toString());
                    $this$newDrmExtractorLink.setKid(StringsKt.trim(this.$drmKidBase64).toString());
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.SKTech$loadLinks$5, reason: invalid class name */
    /* JADX INFO: compiled from: SKTech.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/DrmExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.SKTech$loadLinks$5", f = "SKTech.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass5 extends SuspendLambda implements Function2<DrmExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ Map<String, String> $headers;
        final /* synthetic */ LoadData $loadData;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(Map<String, String> map, LoadData loadData, Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
            this.$headers = map;
            this.$loadData = loadData;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass5 = new AnonymousClass5(this.$headers, this.$loadData, continuation);
            anonymousClass5.L$0 = obj;
            return anonymousClass5;
        }

        public final Object invoke(DrmExtractorLink drmExtractorLink, Continuation<? super Unit> continuation) {
            return create(drmExtractorLink, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            DrmExtractorLink $this$newDrmExtractorLink = (DrmExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newDrmExtractorLink.setQuality(Qualities.Unknown.getValue());
                    if (!this.$headers.isEmpty()) {
                        $this$newDrmExtractorLink.setHeaders(this.$headers);
                    }
                    $this$newDrmExtractorLink.setLicenseUrl(StringsKt.trim(this.$loadData.getLicenseUrl()).toString());
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.SKTech$loadLinks$6, reason: invalid class name */
    /* JADX INFO: compiled from: SKTech.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.SKTech$loadLinks$6", f = "SKTech.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass6 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ Map<String, String> $headers;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass6(Map<String, String> map, Continuation<? super AnonymousClass6> continuation) {
            super(2, continuation);
            this.$headers = map;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass6 = new AnonymousClass6(this.$headers, continuation);
            anonymousClass6.L$0 = obj;
            return anonymousClass6;
        }

        public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
            return create(extractorLink, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            ExtractorLink $this$newExtractorLink = (ExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newExtractorLink.setReferer("");
                    $this$newExtractorLink.setQuality(Qualities.Unknown.getValue());
                    if (!this.$headers.isEmpty()) {
                        $this$newExtractorLink.setHeaders(this.$headers);
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.SKTech$loadLinks$7, reason: invalid class name */
    /* JADX INFO: compiled from: SKTech.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.SKTech$loadLinks$7", f = "SKTech.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass7 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ Map<String, String> $headers;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass7(Map<String, String> map, Continuation<? super AnonymousClass7> continuation) {
            super(2, continuation);
            this.$headers = map;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass7 = new AnonymousClass7(this.$headers, continuation);
            anonymousClass7.L$0 = obj;
            return anonymousClass7;
        }

        public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
            return create(extractorLink, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            ExtractorLink $this$newExtractorLink = (ExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newExtractorLink.setReferer("");
                    $this$newExtractorLink.setQuality(Qualities.Unknown.getValue());
                    if (!this.$headers.isEmpty()) {
                        $this$newExtractorLink.setHeaders(this.$headers);
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.SKTech$loadLinks$8, reason: invalid class name */
    /* JADX INFO: compiled from: SKTech.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.SKTech$loadLinks$8", f = "SKTech.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass8 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ Map<String, String> $headers;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass8(Map<String, String> map, Continuation<? super AnonymousClass8> continuation) {
            super(2, continuation);
            this.$headers = map;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass8 = new AnonymousClass8(this.$headers, continuation);
            anonymousClass8.L$0 = obj;
            return anonymousClass8;
        }

        public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
            return create(extractorLink, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            ExtractorLink $this$newExtractorLink = (ExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newExtractorLink.setReferer("");
                    $this$newExtractorLink.setQuality(Qualities.Unknown.getValue());
                    $this$newExtractorLink.setHeaders(this.$headers);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.SKTech$loadLinks$9, reason: invalid class name */
    /* JADX INFO: compiled from: SKTech.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.SKTech$loadLinks$9", f = "SKTech.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass9 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ Map<String, String> $headers;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass9(Map<String, String> map, Continuation<? super AnonymousClass9> continuation) {
            super(2, continuation);
            this.$headers = map;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass9 = new AnonymousClass9(this.$headers, continuation);
            anonymousClass9.L$0 = obj;
            return anonymousClass9;
        }

        public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
            return create(extractorLink, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            ExtractorLink $this$newExtractorLink = (ExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newExtractorLink.setReferer("");
                    $this$newExtractorLink.setQuality(Qualities.Unknown.getValue());
                    if (!this.$headers.isEmpty()) {
                        $this$newExtractorLink.setHeaders(this.$headers);
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
