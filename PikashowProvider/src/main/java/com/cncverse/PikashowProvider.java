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
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.ExtensionsKt;
import com.lagradost.cloudstream3.Episode;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MovieSearchResponse;
import com.lagradost.cloudstream3.SearchQuality;
import com.lagradost.cloudstream3.TvSeriesSearchResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: PikashowProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nPikashowProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PikashowProvider.kt\ncom/cncverse/PikashowProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1253:1\n1915#2:1254\n1642#2,10:1268\n1915#2:1278\n1916#2:1280\n1652#2:1281\n1642#2,10:1295\n1915#2:1305\n1916#2:1307\n1652#2:1308\n1916#2:1309\n1915#2:1310\n1915#2,2:1324\n1915#2,2:1339\n1916#2:1341\n1915#2,2:1356\n1807#2,3:1440\n1807#2,3:1443\n1915#2,2:1446\n1915#2:1448\n1915#2,2:1449\n1916#2:1451\n116#3:1255\n54#3:1256\n117#3:1257\n61#3,8:1258\n71#3:1267\n116#3:1282\n54#3:1283\n117#3:1284\n61#3,8:1285\n71#3:1294\n116#3:1311\n54#3:1312\n117#3:1313\n61#3,8:1314\n71#3:1323\n116#3:1326\n54#3:1327\n117#3:1328\n61#3,8:1329\n71#3:1338\n116#3:1342\n54#3:1343\n117#3:1344\n61#3,8:1345\n71#3:1354\n116#3:1358\n54#3:1359\n117#3:1360\n61#3,8:1361\n71#3:1370\n116#3:1372\n54#3:1373\n117#3:1374\n61#3,8:1375\n71#3:1384\n116#3:1385\n54#3:1386\n117#3:1387\n61#3,8:1388\n71#3:1397\n116#3:1399\n54#3:1400\n117#3:1401\n61#3,8:1402\n71#3:1411\n116#3:1413\n54#3:1414\n117#3:1415\n61#3,8:1416\n71#3:1425\n116#3:1427\n54#3:1428\n117#3:1429\n61#3,8:1430\n71#3:1439\n116#3:1452\n54#3:1453\n117#3:1454\n61#3,8:1455\n71#3:1464\n116#3:1465\n54#3:1466\n117#3:1467\n61#3,8:1468\n71#3:1477\n1#4:1266\n1#4:1279\n1#4:1293\n1#4:1306\n1#4:1322\n1#4:1337\n1#4:1353\n1#4:1355\n1#4:1369\n1#4:1371\n1#4:1383\n1#4:1396\n1#4:1398\n1#4:1410\n1#4:1412\n1#4:1424\n1#4:1426\n1#4:1438\n1#4:1463\n1#4:1476\n1#4:1478\n*S KotlinDebug\n*F\n+ 1 PikashowProvider.kt\ncom/cncverse/PikashowProvider\n*L\n229#1:1254\n249#1:1268,10\n249#1:1278\n249#1:1280\n249#1:1281\n270#1:1295,10\n270#1:1305\n270#1:1307\n270#1:1308\n229#1:1309\n348#1:1310\n368#1:1324,2\n396#1:1339,2\n348#1:1341\n483#1:1356,2\n745#1:1440,3\n746#1:1443,3\n750#1:1446,2\n773#1:1448\n774#1:1449,2\n773#1:1451\n248#1:1255\n248#1:1256\n248#1:1257\n248#1:1258,8\n248#1:1267\n269#1:1282\n269#1:1283\n269#1:1284\n269#1:1285,8\n269#1:1294\n367#1:1311\n367#1:1312\n367#1:1313\n367#1:1314,8\n367#1:1323\n395#1:1326\n395#1:1327\n395#1:1328\n395#1:1329,8\n395#1:1338\n476#1:1342\n476#1:1343\n476#1:1344\n476#1:1345,8\n476#1:1354\n530#1:1358\n530#1:1359\n530#1:1360\n530#1:1361,8\n530#1:1370\n612#1:1372\n612#1:1373\n612#1:1374\n612#1:1375,8\n612#1:1384\n648#1:1385\n648#1:1386\n648#1:1387\n648#1:1388,8\n648#1:1397\n656#1:1399\n656#1:1400\n656#1:1401\n656#1:1402,8\n656#1:1411\n685#1:1413\n685#1:1414\n685#1:1415\n685#1:1416,8\n685#1:1425\n723#1:1427\n723#1:1428\n723#1:1429\n723#1:1430,8\n723#1:1439\n873#1:1452\n873#1:1453\n873#1:1454\n873#1:1455,8\n873#1:1464\n910#1:1465\n910#1:1466\n910#1:1467\n910#1:1468,8\n910#1:1477\n248#1:1266\n249#1:1279\n269#1:1293\n270#1:1306\n367#1:1322\n395#1:1337\n476#1:1353\n530#1:1369\n612#1:1383\n648#1:1396\n656#1:1410\n685#1:1424\n723#1:1438\n873#1:1463\n910#1:1476\n*E\n"})
public final class PikashowProvider extends MainAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull

    @Nullable
    private static Context context;

    @NotNull
    private String mainUrl = "https://manoda.co";

    @NotNull
    private String name = "Pikashow";
    private final boolean hasMainPage = true;

    @NotNull
    private String lang = "ta";

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(new TvType[]{TvType.Movie, TvType.TvSeries});

    @NotNull
    private final String apiKey = "picashow-api-secret-key";

    @NotNull
    private final String hmacSecret = "picashow-api-secret-2025";

    @NotNull
    private final ObjectMapper mapper = ExtensionsKt.jacksonObjectMapper();

    @NotNull
    private final String deviceUuid = UUID.randomUUID().toString();

    @NotNull
    private final String gaid = UUID.randomUUID().toString();

    /* JADX INFO: renamed from: com.cncverse.PikashowProvider$addVideoLinksToCallback$1, reason: invalid class name */
    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PikashowProvider", f = "PikashowProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6}, l = {758, 783, 801, 805, 818, 823, 827}, m = "addVideoLinksToCallback", n = {"videoData", "callback", "contentName", "baseHeaders", "finalHeaders", "$this$forEach$iv", "element$iv", "resolution", "url", "linkType", "hasResolutions", "hasLanguageResolutions", "$i$f$forEach", "$i$a$-forEach-PikashowProvider$addVideoLinksToCallback$6", "$i$a$-let-PikashowProvider$addVideoLinksToCallback$6$1", "videoData", "callback", "contentName", "baseHeaders", "finalHeaders", "$this$forEach$iv", "element$iv", "lang", "$this$forEach$iv", "element$iv", "resolution", "url", "linkType", "langName", "hasResolutions", "hasLanguageResolutions", "$i$f$forEach", "$i$a$-forEach-PikashowProvider$addVideoLinksToCallback$7", "$i$f$forEach", "$i$a$-forEach-PikashowProvider$addVideoLinksToCallback$7$1", "$i$a$-let-PikashowProvider$addVideoLinksToCallback$7$1$1", "videoData", "callback", "contentName", "baseHeaders", "finalHeaders", "hasResolutions", "hasLanguageResolutions", "videoData", "callback", "contentName", "baseHeaders", "finalHeaders", "streamingUrl", "urlOrigin", "hasResolutions", "hasLanguageResolutions", "videoData", "callback", "contentName", "baseHeaders", "finalHeaders", "streamingUrl", "hasResolutions", "hasLanguageResolutions", "videoData", "callback", "contentName", "baseHeaders", "finalHeaders", "e", "hasResolutions", "hasLanguageResolutions", "videoData", "callback", "contentName", "baseHeaders", "finalHeaders", "hasResolutions", "hasLanguageResolutions"}, nl = {757, 782, 802, 804, 820, 827, 832}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$9", "L$10", "I$0", "I$1", "I$2", "I$3", "I$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$9", "L$11", "L$12", "L$13", "L$14", "L$15", "I$0", "I$1", "I$2", "I$3", "I$4", "I$5", "I$6", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        int I$6;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PikashowProvider.this.addVideoLinksToCallback(null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.PikashowProvider$fallbackToDirectUrls$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PikashowProvider", f = "PikashowProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {975}, m = "fallbackToDirectUrls", n = {"videoData", "callback", "contentName", "finalHeaders", "directUrl", "url", "linkType", "$i$a$-let-PikashowProvider$fallbackToDirectUrls$2", "quality"}, nl = {974}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "I$1"}, v = 2)
    static final class C00001 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        C00001(Continuation<? super C00001> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PikashowProvider.this.fallbackToDirectUrls(null, null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.PikashowProvider$getMainPage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PikashowProvider", f = "PikashowProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {237}, m = "getMainPage", n = {"request", "headers", "homePageList", "categories", "$this$forEach$iv", "element$iv", "type", "displayName", "url", "params", "page", "$i$f$forEach", "$i$a$-forEach-PikashowProvider$getMainPage$2"}, nl = {244}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "L$7", "L$8", "L$9", "L$10", "I$0", "I$1", "I$2"}, v = 2)
    static final class C00011 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        C00011(Continuation<? super C00011> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PikashowProvider.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.PikashowProvider$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PikashowProvider", f = "PikashowProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {468, 498, 522, 534}, m = "load", n = {"url", "withoutUrlScheme", "parts", "identifier", "type", "headers", "seriesUrl", "params", "url", "withoutUrlScheme", "parts", "identifier", "type", "headers", "seriesUrl", "params", "response", "seriesResponse", "series", "seriesData", "episodes", "$i$a$-let-PikashowProvider$load$2", "url", "withoutUrlScheme", "parts", "identifier", "type", "headers", "movieUrl", "params", "url", "withoutUrlScheme", "parts", "identifier", "type", "headers", "movieUrl", "params", "response", "movieResponse", "movie", "movieData", "$i$a$-let-PikashowProvider$load$3"}, nl = {475, 509, 529, 545}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "I$0"}, v = 2)
    static final class C00021 extends ContinuationImpl {
        int I$0;
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
        int label;
        /* synthetic */ Object result;

        C00021(Continuation<? super C00021> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PikashowProvider.this.load(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.PikashowProvider$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PikashowProvider", f = "PikashowProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, l = {604, 614, 635, 678, 688}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "withoutUrlScheme", "headers", "parts", "seriesTitle", "season", "episode", "videoUrl", "params", "isCasting", "data", "subtitleCallback", "callback", "withoutUrlScheme", "headers", "parts", "seriesTitle", "season", "episode", "videoUrl", "params", "response", "videoResponse", "videoData", "isCasting", "$i$a$-let-PikashowProvider$loadLinks$3", "data", "subtitleCallback", "callback", "withoutUrlScheme", "headers", "parts", "identifier", "type", "listUrl", "listParams", "isCasting", "data", "subtitleCallback", "callback", "withoutUrlScheme", "headers", "parts", "identifier", "type", "listUrl", "listParams", "listResponse", "videoId", "title", "safeTitle", "videoUrl", "videoParams", "isCasting", "data", "subtitleCallback", "callback", "withoutUrlScheme", "headers", "parts", "identifier", "type", "listUrl", "listParams", "listResponse", "videoId", "title", "safeTitle", "videoUrl", "videoParams", "videoResponse", "videoApiResponse", "contentNameLocal", "videoData", "isCasting", "$i$a$-let-PikashowProvider$loadLinks$6"}, nl = {611, 615, 642, 684, 689}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$17", "L$18", "L$19", "Z$0", "I$0"}, v = 2)
    static final class C00031 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$17;
        Object L$18;
        Object L$19;
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

        C00031(Continuation<? super C00031> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PikashowProvider.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.PikashowProvider$parseHDBVPlayerUrl$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PikashowProvider", f = "PikashowProvider.kt", i = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {837, 890, 918}, m = "parseHDBVPlayerUrl", n = {"playerUrl", "playerUrl", "response", "doc", "scripts", "script", "regex", "matchResult", "jsonInsideHDVBPlayer", "fileKeys", "origin", "absoluteUrl", "headers", "referer", "playerUrl", "response", "doc", "scripts", "script", "regex", "matchResult", "jsonInsideHDVBPlayer", "fileKeys", "origin", "absoluteUrl", "headers", "referer", "postResponse", "responseText", "jsonArray", "seasons", "episodeDetails", "episode"}, nl = {860, 896, 924}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$17", "L$18"}, v = 2)
    static final class C00041 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$17;
        Object L$18;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        C00041(Continuation<? super C00041> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PikashowProvider.this.parseHDBVPlayerUrl(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.PikashowProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PikashowProvider", f = "PikashowProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {356}, m = "search", n = {"query", "searchResults", "headers", "searchQuery", "categories", "$this$forEach$iv", "element$iv", "type", "tvType", "url", "params", "$i$f$forEach", "$i$a$-forEach-PikashowProvider$search$2"}, nl = {363}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$9", "L$10", "L$11", "I$0", "I$1"}, v = 2)
    static final class C00051 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        C00051(Continuation<? super C00051> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PikashowProvider.this.search(null, (Continuation) this);
        }
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

    @NotNull
    public String getLang() {
        return this.lang;
    }

    public void setLang(@NotNull String str) {
        this.lang = str;
    }

    @NotNull
    public Set<TvType> getSupportedTypes() {
        return this.supportedTypes;
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/cncverse/PikashowProvider$Companion;", "", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "OMG10", "", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return PikashowProvider.context;
        }

        public final void setContext(@Nullable Context context) {
            PikashowProvider.context = context;
        }
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Ba\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0003\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0013J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0011\u0010 \u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÆ\u0003Jh\u0010!\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00062\u0010\b\u0003\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u0010\"J\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010&\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010'\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0017\u0010\u0013R\u001e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006("}, d2 = {"Lcom/cncverse/PikashowProvider$PikashowSeries;", "", "title", "", "genre", "year", "", "cover", "imdbRating", "seasons", "details", "", "Lcom/cncverse/PikashowProvider$SeasonDetail;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;)V", "getTitle", "()Ljava/lang/String;", "getGenre", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCover", "getImdbRating", "getSeasons", "getDetails", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;)Lcom/cncverse/PikashowProvider$PikashowSeries;", "equals", "", "other", "hashCode", "toString", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class PikashowSeries {

        @JsonProperty("c")
        @Nullable
        private final String cover;

        @JsonProperty("detail")
        @Nullable
        private final List<SeasonDetail> details;

        @JsonProperty("g")
        @Nullable
        private final String genre;

        @JsonProperty("i")
        @Nullable
        private final String imdbRating;

        @JsonProperty("n")
        @Nullable
        private final Integer seasons;

        @JsonProperty("t")
        @Nullable
        private final String title;

        @JsonProperty("y")
        @Nullable
        private final Integer year;

        public PikashowSeries() {
            this(null, null, null, null, null, null, null, 127, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ PikashowSeries copy$default(PikashowSeries pikashowSeries, String str, String str2, Integer num, String str3, String str4, Integer num2, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = pikashowSeries.title;
            }
            if ((i & 2) != 0) {
                str2 = pikashowSeries.genre;
            }
            if ((i & 4) != 0) {
                num = pikashowSeries.year;
            }
            if ((i & 8) != 0) {
                str3 = pikashowSeries.cover;
            }
            if ((i & 16) != 0) {
                str4 = pikashowSeries.imdbRating;
            }
            if ((i & 32) != 0) {
                num2 = pikashowSeries.seasons;
            }
            if ((i & 64) != 0) {
                list = pikashowSeries.details;
            }
            Integer num3 = num2;
            List list2 = list;
            String str5 = str4;
            Integer num4 = num;
            return pikashowSeries.copy(str, str2, num4, str3, str5, num3, list2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getGenre() {
            return this.genre;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getCover() {
            return this.cover;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getImdbRating() {
            return this.imdbRating;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getSeasons() {
            return this.seasons;
        }

        @Nullable
        public final List<SeasonDetail> component7() {
            return this.details;
        }

        @NotNull
        public final PikashowSeries copy(@JsonProperty("t") @Nullable String title, @JsonProperty("g") @Nullable String genre, @JsonProperty("y") @Nullable Integer year, @JsonProperty("c") @Nullable String cover, @JsonProperty("i") @Nullable String imdbRating, @JsonProperty("n") @Nullable Integer seasons, @JsonProperty("detail") @Nullable List<SeasonDetail> details) {
            return new PikashowSeries(title, genre, year, cover, imdbRating, seasons, details);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PikashowSeries)) {
                return false;
            }
            PikashowSeries pikashowSeries = (PikashowSeries) other;
            return Intrinsics.areEqual(this.title, pikashowSeries.title) && Intrinsics.areEqual(this.genre, pikashowSeries.genre) && Intrinsics.areEqual(this.year, pikashowSeries.year) && Intrinsics.areEqual(this.cover, pikashowSeries.cover) && Intrinsics.areEqual(this.imdbRating, pikashowSeries.imdbRating) && Intrinsics.areEqual(this.seasons, pikashowSeries.seasons) && Intrinsics.areEqual(this.details, pikashowSeries.details);
        }

        public int hashCode() {
            return ((((((((((((this.title == null ? 0 : this.title.hashCode()) * 31) + (this.genre == null ? 0 : this.genre.hashCode())) * 31) + (this.year == null ? 0 : this.year.hashCode())) * 31) + (this.cover == null ? 0 : this.cover.hashCode())) * 31) + (this.imdbRating == null ? 0 : this.imdbRating.hashCode())) * 31) + (this.seasons == null ? 0 : this.seasons.hashCode())) * 31) + (this.details != null ? this.details.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "PikashowSeries(title=" + this.title + ", genre=" + this.genre + ", year=" + this.year + ", cover=" + this.cover + ", imdbRating=" + this.imdbRating + ", seasons=" + this.seasons + ", details=" + this.details + ')';
        }

        public PikashowSeries(@JsonProperty("t") @Nullable String title, @JsonProperty("g") @Nullable String genre, @JsonProperty("y") @Nullable Integer year, @JsonProperty("c") @Nullable String cover, @JsonProperty("i") @Nullable String imdbRating, @JsonProperty("n") @Nullable Integer seasons, @JsonProperty("detail") @Nullable List<SeasonDetail> list) {
            this.title = title;
            this.genre = genre;
            this.year = year;
            this.cover = cover;
            this.imdbRating = imdbRating;
            this.seasons = seasons;
            this.details = list;
        }

        public /* synthetic */ PikashowSeries(String str, String str2, Integer num, String str3, String str4, Integer num2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : num2, (i & 64) != 0 ? null : list);
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final String getGenre() {
            return this.genre;
        }

        @Nullable
        public final Integer getYear() {
            return this.year;
        }

        @Nullable
        public final String getCover() {
            return this.cover;
        }

        @Nullable
        public final String getImdbRating() {
            return this.imdbRating;
        }

        @Nullable
        public final Integer getSeasons() {
            return this.seasons;
        }

        @Nullable
        public final List<SeasonDetail> getDetails() {
            return this.details;
        }
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ2\u0010\u0012\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/cncverse/PikashowProvider$SeasonDetail;", "", "year", "", "season", "", "episodesCount", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;)V", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSeason", "()Ljava/lang/String;", "getEpisodesCount", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;)Lcom/cncverse/PikashowProvider$SeasonDetail;", "equals", "", "other", "hashCode", "toString", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SeasonDetail {

        @JsonProperty("episodes_count")
        @Nullable
        private final Integer episodesCount;

        @JsonProperty("season")
        @Nullable
        private final String season;

        @JsonProperty("year")
        @Nullable
        private final Integer year;

        public SeasonDetail() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ SeasonDetail copy$default(SeasonDetail seasonDetail, Integer num, String str, Integer num2, int i, Object obj) {
            if ((i & 1) != 0) {
                num = seasonDetail.year;
            }
            if ((i & 2) != 0) {
                str = seasonDetail.season;
            }
            if ((i & 4) != 0) {
                num2 = seasonDetail.episodesCount;
            }
            return seasonDetail.copy(num, str, num2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSeason() {
            return this.season;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getEpisodesCount() {
            return this.episodesCount;
        }

        @NotNull
        public final SeasonDetail copy(@JsonProperty("year") @Nullable Integer year, @JsonProperty("season") @Nullable String season, @JsonProperty("episodes_count") @Nullable Integer episodesCount) {
            return new SeasonDetail(year, season, episodesCount);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SeasonDetail)) {
                return false;
            }
            SeasonDetail seasonDetail = (SeasonDetail) other;
            return Intrinsics.areEqual(this.year, seasonDetail.year) && Intrinsics.areEqual(this.season, seasonDetail.season) && Intrinsics.areEqual(this.episodesCount, seasonDetail.episodesCount);
        }

        public int hashCode() {
            return ((((this.year == null ? 0 : this.year.hashCode()) * 31) + (this.season == null ? 0 : this.season.hashCode())) * 31) + (this.episodesCount != null ? this.episodesCount.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "SeasonDetail(year=" + this.year + ", season=" + this.season + ", episodesCount=" + this.episodesCount + ')';
        }

        public SeasonDetail(@JsonProperty("year") @Nullable Integer year, @JsonProperty("season") @Nullable String season, @JsonProperty("episodes_count") @Nullable Integer episodesCount) {
            this.year = year;
            this.season = season;
            this.episodesCount = episodesCount;
        }

        public /* synthetic */ SeasonDetail(Integer num, String str, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : num2);
        }

        @Nullable
        public final Integer getYear() {
            return this.year;
        }

        @Nullable
        public final String getSeason() {
            return this.season;
        }

        @Nullable
        public final Integer getEpisodesCount() {
            return this.episodesCount;
        }
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\n\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/cncverse/PikashowProvider$PikashowSeriesResponse;", "", "series", "", "Lcom/cncverse/PikashowProvider$PikashowSeries;", "<init>", "(Ljava/util/List;)V", "getSeries", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class PikashowSeriesResponse {

        @JsonProperty("series")
        @Nullable
        private final List<PikashowSeries> series;

        /* JADX WARN: Illegal instructions before constructor call */
        public PikashowSeriesResponse() {
            List list = null;
            this(list, 1, list);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ PikashowSeriesResponse copy$default(PikashowSeriesResponse pikashowSeriesResponse, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = pikashowSeriesResponse.series;
            }
            return pikashowSeriesResponse.copy(list);
        }

        @Nullable
        public final List<PikashowSeries> component1() {
            return this.series;
        }

        @NotNull
        public final PikashowSeriesResponse copy(@JsonProperty("series") @Nullable List<PikashowSeries> series) {
            return new PikashowSeriesResponse(series);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof PikashowSeriesResponse) && Intrinsics.areEqual(this.series, ((PikashowSeriesResponse) other).series);
        }

        public int hashCode() {
            if (this.series == null) {
                return 0;
            }
            return this.series.hashCode();
        }

        @NotNull
        public String toString() {
            return "PikashowSeriesResponse(series=" + this.series + ')';
        }

        public PikashowSeriesResponse(@JsonProperty("series") @Nullable List<PikashowSeries> list) {
            this.series = list;
        }

        public /* synthetic */ PikashowSeriesResponse(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : list);
        }

        @Nullable
        public final List<PikashowSeries> getSeries() {
            return this.series;
        }
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001By\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0012J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0012J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0011\u0010&\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÆ\u0003J\u0080\u0001\u0010'\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÆ\u0001¢\u0006\u0002\u0010(J\u0014\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010,\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010-\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0017\u0010\u0012R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u001b\u0010\u0012R\u001e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006."}, d2 = {"Lcom/cncverse/PikashowProvider$PikashowMovie;", "", "sortOrder", "", "title", "", "genre", "year", "quality", "cover", "url", "format", "clientUrls", "", "Lcom/cncverse/PikashowProvider$ClientUrl;", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;)V", "getSortOrder", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitle", "()Ljava/lang/String;", "getGenre", "getYear", "getQuality", "getCover", "getUrl", "getFormat", "getClientUrls", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;)Lcom/cncverse/PikashowProvider$PikashowMovie;", "equals", "", "other", "hashCode", "toString", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class PikashowMovie {

        @JsonProperty("clientUrls")
        @Nullable
        private final List<ClientUrl> clientUrls;

        @JsonProperty("c")
        @Nullable
        private final String cover;

        @JsonProperty("f")
        @Nullable
        private final Integer format;

        @JsonProperty("g")
        @Nullable
        private final String genre;

        @JsonProperty("q")
        @Nullable
        private final String quality;

        @JsonProperty("so")
        @Nullable
        private final Integer sortOrder;

        @JsonProperty("t")
        @Nullable
        private final String title;

        @JsonProperty("url")
        @Nullable
        private final String url;

        @JsonProperty("y")
        @Nullable
        private final Integer year;

        public PikashowMovie() {
            this(null, null, null, null, null, null, null, null, null, 511, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ PikashowMovie copy$default(PikashowMovie pikashowMovie, Integer num, String str, String str2, Integer num2, String str3, String str4, String str5, Integer num3, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                num = pikashowMovie.sortOrder;
            }
            if ((i & 2) != 0) {
                str = pikashowMovie.title;
            }
            if ((i & 4) != 0) {
                str2 = pikashowMovie.genre;
            }
            if ((i & 8) != 0) {
                num2 = pikashowMovie.year;
            }
            if ((i & 16) != 0) {
                str3 = pikashowMovie.quality;
            }
            if ((i & 32) != 0) {
                str4 = pikashowMovie.cover;
            }
            if ((i & 64) != 0) {
                str5 = pikashowMovie.url;
            }
            if ((i & 128) != 0) {
                num3 = pikashowMovie.format;
            }
            if ((i & 256) != 0) {
                list = pikashowMovie.clientUrls;
            }
            Integer num4 = num3;
            List list2 = list;
            String str6 = str4;
            String str7 = str5;
            String str8 = str3;
            String str9 = str2;
            return pikashowMovie.copy(num, str, str9, num2, str8, str6, str7, num4, list2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getSortOrder() {
            return this.sortOrder;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getGenre() {
            return this.genre;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getQuality() {
            return this.quality;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getCover() {
            return this.cover;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getFormat() {
            return this.format;
        }

        @Nullable
        public final List<ClientUrl> component9() {
            return this.clientUrls;
        }

        @NotNull
        public final PikashowMovie copy(@JsonProperty("so") @Nullable Integer sortOrder, @JsonProperty("t") @Nullable String title, @JsonProperty("g") @Nullable String genre, @JsonProperty("y") @Nullable Integer year, @JsonProperty("q") @Nullable String quality, @JsonProperty("c") @Nullable String cover, @JsonProperty("url") @Nullable String url, @JsonProperty("f") @Nullable Integer format, @JsonProperty("clientUrls") @Nullable List<ClientUrl> clientUrls) {
            return new PikashowMovie(sortOrder, title, genre, year, quality, cover, url, format, clientUrls);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PikashowMovie)) {
                return false;
            }
            PikashowMovie pikashowMovie = (PikashowMovie) other;
            return Intrinsics.areEqual(this.sortOrder, pikashowMovie.sortOrder) && Intrinsics.areEqual(this.title, pikashowMovie.title) && Intrinsics.areEqual(this.genre, pikashowMovie.genre) && Intrinsics.areEqual(this.year, pikashowMovie.year) && Intrinsics.areEqual(this.quality, pikashowMovie.quality) && Intrinsics.areEqual(this.cover, pikashowMovie.cover) && Intrinsics.areEqual(this.url, pikashowMovie.url) && Intrinsics.areEqual(this.format, pikashowMovie.format) && Intrinsics.areEqual(this.clientUrls, pikashowMovie.clientUrls);
        }

        public int hashCode() {
            return ((((((((((((((((this.sortOrder == null ? 0 : this.sortOrder.hashCode()) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.genre == null ? 0 : this.genre.hashCode())) * 31) + (this.year == null ? 0 : this.year.hashCode())) * 31) + (this.quality == null ? 0 : this.quality.hashCode())) * 31) + (this.cover == null ? 0 : this.cover.hashCode())) * 31) + (this.url == null ? 0 : this.url.hashCode())) * 31) + (this.format == null ? 0 : this.format.hashCode())) * 31) + (this.clientUrls != null ? this.clientUrls.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "PikashowMovie(sortOrder=" + this.sortOrder + ", title=" + this.title + ", genre=" + this.genre + ", year=" + this.year + ", quality=" + this.quality + ", cover=" + this.cover + ", url=" + this.url + ", format=" + this.format + ", clientUrls=" + this.clientUrls + ')';
        }

        public PikashowMovie(@JsonProperty("so") @Nullable Integer sortOrder, @JsonProperty("t") @Nullable String title, @JsonProperty("g") @Nullable String genre, @JsonProperty("y") @Nullable Integer year, @JsonProperty("q") @Nullable String quality, @JsonProperty("c") @Nullable String cover, @JsonProperty("url") @Nullable String url, @JsonProperty("f") @Nullable Integer format, @JsonProperty("clientUrls") @Nullable List<ClientUrl> list) {
            this.sortOrder = sortOrder;
            this.title = title;
            this.genre = genre;
            this.year = year;
            this.quality = quality;
            this.cover = cover;
            this.url = url;
            this.format = format;
            this.clientUrls = list;
        }

        public /* synthetic */ PikashowMovie(Integer num, String str, String str2, Integer num2, String str3, String str4, String str5, Integer num3, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : num3, (i & 256) != 0 ? null : list);
        }

        @Nullable
        public final Integer getSortOrder() {
            return this.sortOrder;
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final String getGenre() {
            return this.genre;
        }

        @Nullable
        public final Integer getYear() {
            return this.year;
        }

        @Nullable
        public final String getQuality() {
            return this.quality;
        }

        @Nullable
        public final String getCover() {
            return this.cover;
        }

        @Nullable
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        public final Integer getFormat() {
            return this.format;
        }

        @Nullable
        public final List<ClientUrl> getClientUrls() {
            return this.clientUrls;
        }
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/cncverse/PikashowProvider$ClientUrl;", "", "label", "", "url", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "getUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ClientUrl {

        @JsonProperty("label")
        @Nullable
        private final String label;

        @JsonProperty("url")
        @Nullable
        private final String url;

        /* JADX WARN: Illegal instructions before constructor call */
        public ClientUrl() {
            String str = null;
            this(str, str, 3, str);
        }

        public static /* synthetic */ ClientUrl copy$default(ClientUrl clientUrl, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = clientUrl.label;
            }
            if ((i & 2) != 0) {
                str2 = clientUrl.url;
            }
            return clientUrl.copy(str, str2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @NotNull
        public final ClientUrl copy(@JsonProperty("label") @Nullable String label, @JsonProperty("url") @Nullable String url) {
            return new ClientUrl(label, url);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ClientUrl)) {
                return false;
            }
            ClientUrl clientUrl = (ClientUrl) other;
            return Intrinsics.areEqual(this.label, clientUrl.label) && Intrinsics.areEqual(this.url, clientUrl.url);
        }

        public int hashCode() {
            return ((this.label == null ? 0 : this.label.hashCode()) * 31) + (this.url != null ? this.url.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "ClientUrl(label=" + this.label + ", url=" + this.url + ')';
        }

        public ClientUrl(@JsonProperty("label") @Nullable String label, @JsonProperty("url") @Nullable String url) {
            this.label = label;
            this.url = url;
        }

        public /* synthetic */ ClientUrl(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
        }

        @Nullable
        public final String getLabel() {
            return this.label;
        }

        @Nullable
        public final String getUrl() {
            return this.url;
        }
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\n\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/cncverse/PikashowProvider$PikashowMovieResponse;", "", "records", "", "Lcom/cncverse/PikashowProvider$PikashowMovie;", "<init>", "(Ljava/util/List;)V", "getRecords", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class PikashowMovieResponse {

        @JsonProperty("records")
        @Nullable
        private final List<PikashowMovie> records;

        /* JADX WARN: Illegal instructions before constructor call */
        public PikashowMovieResponse() {
            List list = null;
            this(list, 1, list);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ PikashowMovieResponse copy$default(PikashowMovieResponse pikashowMovieResponse, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = pikashowMovieResponse.records;
            }
            return pikashowMovieResponse.copy(list);
        }

        @Nullable
        public final List<PikashowMovie> component1() {
            return this.records;
        }

        @NotNull
        public final PikashowMovieResponse copy(@JsonProperty("records") @Nullable List<PikashowMovie> records) {
            return new PikashowMovieResponse(records);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof PikashowMovieResponse) && Intrinsics.areEqual(this.records, ((PikashowMovieResponse) other).records);
        }

        public int hashCode() {
            if (this.records == null) {
                return 0;
            }
            return this.records.hashCode();
        }

        @NotNull
        public String toString() {
            return "PikashowMovieResponse(records=" + this.records + ')';
        }

        public PikashowMovieResponse(@JsonProperty("records") @Nullable List<PikashowMovie> list) {
            this.records = list;
        }

        public /* synthetic */ PikashowMovieResponse(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : list);
        }

        @Nullable
        public final List<PikashowMovie> getRecords() {
            return this.records;
        }
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J2\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/cncverse/PikashowProvider$VideoApiResponse;", "", "code", "", "message", "", "data", "Lcom/cncverse/PikashowProvider$VideoData;", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/cncverse/PikashowProvider$VideoData;)V", "getCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMessage", "()Ljava/lang/String;", "getData", "()Lcom/cncverse/PikashowProvider$VideoData;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/cncverse/PikashowProvider$VideoData;)Lcom/cncverse/PikashowProvider$VideoApiResponse;", "equals", "", "other", "hashCode", "toString", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VideoApiResponse {

        @JsonProperty("code")
        @Nullable
        private final Integer code;

        @JsonProperty("data")
        @Nullable
        private final VideoData data;

        @JsonProperty("message")
        @Nullable
        private final String message;

        public VideoApiResponse() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ VideoApiResponse copy$default(VideoApiResponse videoApiResponse, Integer num, String str, VideoData videoData, int i, Object obj) {
            if ((i & 1) != 0) {
                num = videoApiResponse.code;
            }
            if ((i & 2) != 0) {
                str = videoApiResponse.message;
            }
            if ((i & 4) != 0) {
                videoData = videoApiResponse.data;
            }
            return videoApiResponse.copy(num, str, videoData);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getCode() {
            return this.code;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final VideoData getData() {
            return this.data;
        }

        @NotNull
        public final VideoApiResponse copy(@JsonProperty("code") @Nullable Integer code, @JsonProperty("message") @Nullable String message, @JsonProperty("data") @Nullable VideoData data) {
            return new VideoApiResponse(code, message, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoApiResponse)) {
                return false;
            }
            VideoApiResponse videoApiResponse = (VideoApiResponse) other;
            return Intrinsics.areEqual(this.code, videoApiResponse.code) && Intrinsics.areEqual(this.message, videoApiResponse.message) && Intrinsics.areEqual(this.data, videoApiResponse.data);
        }

        public int hashCode() {
            return ((((this.code == null ? 0 : this.code.hashCode()) * 31) + (this.message == null ? 0 : this.message.hashCode())) * 31) + (this.data != null ? this.data.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "VideoApiResponse(code=" + this.code + ", message=" + this.message + ", data=" + this.data + ')';
        }

        public VideoApiResponse(@JsonProperty("code") @Nullable Integer code, @JsonProperty("message") @Nullable String message, @JsonProperty("data") @Nullable VideoData data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public /* synthetic */ VideoApiResponse(Integer num, String str, VideoData videoData, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : videoData);
        }

        @Nullable
        public final Integer getCode() {
            return this.code;
        }

        @Nullable
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        public final VideoData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\bP\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0093\u0003\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0003\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0003\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000b\u0012\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u000b\u0012\u0016\b\u0003\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0018\u0012\u0010\b\u0003\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u000b\u0012\u0010\b\u0003\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u000b\u0012\n\b\u0003\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u001f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010!\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\"\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010#\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b\u0012\n\b\u0003\u0010%\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010&\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b'\u0010(J\u000b\u0010K\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010M\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010-J\u000b\u0010N\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010P\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010-J\u0011\u0010Q\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÆ\u0003J\u0010\u0010R\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010-J\u000b\u0010S\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010U\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010-J\u0011\u0010V\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000bHÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010Y\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u000bHÆ\u0003J\u0017\u0010Z\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0018HÆ\u0003J\u0011\u0010[\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u000bHÆ\u0003J\u0011\u0010\\\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u000bHÆ\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000bHÆ\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u009a\u0003\u0010h\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00062\u0010\b\u0003\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0010\b\u0003\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000b2\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u000b2\u0016\b\u0003\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00182\u0010\b\u0003\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u000b2\u0010\b\u0003\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u000b2\n\b\u0003\u0010\u001c\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u001f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010 \u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010!\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\"\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010#\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b2\n\b\u0003\u0010%\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010iJ\u0014\u0010j\u001a\u00020k2\b\u0010l\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010m\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010n\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010*R\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010.\u001a\u0004\b,\u0010-R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010*R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010*R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010.\u001a\u0004\b1\u0010-R\u001e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u001a\u0010\r\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010.\u001a\u0004\b4\u0010-R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010*R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010*R\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010.\u001a\u0004\b7\u0010-R\u001e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u00103R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010*R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010*R\u001e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u00103R$\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00188\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u001e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u00103R\u001e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u00103R\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010*R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010*R\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010*R\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u0010*R\u0018\u0010 \u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010*R\u0018\u0010!\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u0010*R\u0018\u0010\"\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010*R\u0018\u0010#\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u0010*R\u001e\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u00103R\u0018\u0010%\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010*R\u0018\u0010&\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010*¨\u0006o"}, d2 = {"Lcom/cncverse/PikashowProvider$VideoData;", "", "title", "", "genre", "year", "", "cover", "imdbRating", "seasons", "details", "", "Lcom/cncverse/PikashowProvider$VideoSeasonDetail;", "sortOrder", "quality", "url", "format", "clientUrls", "Lcom/cncverse/PikashowProvider$ClientUrl;", "videoUrl", "playUrl", "resolutions", "Lcom/cncverse/PikashowProvider$Resolution;", "headers", "", "languages", "Lcom/cncverse/PikashowProvider$Language;", "languageOptions", "heastr", "uastr", "uaStr", "headerStr", "sourceType", "host", "file", "key", "supportedLanguages", "season", "episode", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/Map;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getGenre", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCover", "getImdbRating", "getSeasons", "getDetails", "()Ljava/util/List;", "getSortOrder", "getQuality", "getUrl", "getFormat", "getClientUrls", "getVideoUrl", "getPlayUrl", "getResolutions", "getHeaders", "()Ljava/util/Map;", "getLanguages", "getLanguageOptions", "getHeastr", "getUastr", "getUaStr", "getHeaderStr", "getSourceType", "getHost", "getFile", "getKey", "getSupportedLanguages", "getSeason", "getEpisode", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/Map;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)Lcom/cncverse/PikashowProvider$VideoData;", "equals", "", "other", "hashCode", "toString", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VideoData {

        @JsonProperty("clientUrls")
        @Nullable
        private final List<ClientUrl> clientUrls;

        @JsonProperty("c")
        @Nullable
        private final String cover;

        @JsonProperty("detail")
        @Nullable
        private final List<VideoSeasonDetail> details;

        @JsonProperty("episode")
        @Nullable
        private final String episode;

        @JsonProperty("file")
        @Nullable
        private final String file;

        @JsonProperty("f")
        @Nullable
        private final Integer format;

        @JsonProperty("g")
        @Nullable
        private final String genre;

        @JsonProperty("headerStr")
        @Nullable
        private final String headerStr;

        @JsonProperty("headers")
        @Nullable
        private final Map<String, String> headers;

        @JsonProperty("heastr")
        @Nullable
        private final String heastr;

        @JsonProperty("host")
        @Nullable
        private final String host;

        @JsonProperty("i")
        @Nullable
        private final String imdbRating;

        @JsonProperty("key")
        @Nullable
        private final String key;

        @JsonProperty("languageOptions")
        @Nullable
        private final List<Language> languageOptions;

        @JsonProperty("languages")
        @Nullable
        private final List<Language> languages;

        @JsonProperty("playUrl")
        @Nullable
        private final String playUrl;

        @JsonProperty("q")
        @Nullable
        private final String quality;

        @JsonProperty("resolutions")
        @Nullable
        private final List<Resolution> resolutions;

        @JsonProperty("season")
        @Nullable
        private final String season;

        @JsonProperty("n")
        @Nullable
        private final Integer seasons;

        @JsonProperty("so")
        @Nullable
        private final Integer sortOrder;

        @JsonProperty("sourceType")
        @Nullable
        private final String sourceType;

        @JsonProperty("supportedLanguages")
        @Nullable
        private final List<String> supportedLanguages;

        @JsonProperty("t")
        @Nullable
        private final String title;

        @JsonProperty("uaStr")
        @Nullable
        private final String uaStr;

        @JsonProperty("uastr")
        @Nullable
        private final String uastr;

        @JsonProperty("url")
        @Nullable
        private final String url;

        @JsonProperty("videoUrl")
        @Nullable
        private final String videoUrl;

        @JsonProperty("y")
        @Nullable
        private final Integer year;

        public VideoData() {
            this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 536870911, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ VideoData copy$default(VideoData videoData, String str, String str2, Integer num, String str3, String str4, Integer num2, List list, Integer num3, String str5, String str6, Integer num4, List list2, String str7, String str8, List list3, Map map, List list4, List list5, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, List list6, String str17, String str18, int i, Object obj) {
            String str19;
            String str20;
            String str21 = (i & 1) != 0 ? videoData.title : str;
            String str22 = (i & 2) != 0 ? videoData.genre : str2;
            Integer num5 = (i & 4) != 0 ? videoData.year : num;
            String str23 = (i & 8) != 0 ? videoData.cover : str3;
            String str24 = (i & 16) != 0 ? videoData.imdbRating : str4;
            Integer num6 = (i & 32) != 0 ? videoData.seasons : num2;
            List list7 = (i & 64) != 0 ? videoData.details : list;
            Integer num7 = (i & 128) != 0 ? videoData.sortOrder : num3;
            String str25 = (i & 256) != 0 ? videoData.quality : str5;
            String str26 = (i & 512) != 0 ? videoData.url : str6;
            Integer num8 = (i & 1024) != 0 ? videoData.format : num4;
            List list8 = (i & 2048) != 0 ? videoData.clientUrls : list2;
            String str27 = (i & 4096) != 0 ? videoData.videoUrl : str7;
            String str28 = (i & 8192) != 0 ? videoData.playUrl : str8;
            String str29 = str21;
            List list9 = (i & 16384) != 0 ? videoData.resolutions : list3;
            Map map2 = (i & 32768) != 0 ? videoData.headers : map;
            List list10 = (i & 65536) != 0 ? videoData.languages : list4;
            List list11 = (i & 131072) != 0 ? videoData.languageOptions : list5;
            String str30 = (i & 262144) != 0 ? videoData.heastr : str9;
            String str31 = (i & 524288) != 0 ? videoData.uastr : str10;
            String str32 = (i & 1048576) != 0 ? videoData.uaStr : str11;
            String str33 = (i & 2097152) != 0 ? videoData.headerStr : str12;
            String str34 = (i & 4194304) != 0 ? videoData.sourceType : str13;
            String str35 = (i & 8388608) != 0 ? videoData.host : str14;
            String str36 = (i & 16777216) != 0 ? videoData.file : str15;
            String str37 = (i & 33554432) != 0 ? videoData.key : str16;
            List list12 = (i & 67108864) != 0 ? videoData.supportedLanguages : list6;
            String str38 = (i & 134217728) != 0 ? videoData.season : str17;
            if ((i & 268435456) != 0) {
                str20 = str38;
                str19 = videoData.episode;
            } else {
                str19 = str18;
                str20 = str38;
            }
            return videoData.copy(str29, str22, num5, str23, str24, num6, list7, num7, str25, str26, num8, list8, str27, str28, list9, map2, list10, list11, str30, str31, str32, str33, str34, str35, str36, str37, list12, str20, str19);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        /* JADX INFO: renamed from: component11, reason: from getter */
        public final Integer getFormat() {
            return this.format;
        }

        @Nullable
        public final List<ClientUrl> component12() {
            return this.clientUrls;
        }

        @Nullable
        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getVideoUrl() {
            return this.videoUrl;
        }

        @Nullable
        /* JADX INFO: renamed from: component14, reason: from getter */
        public final String getPlayUrl() {
            return this.playUrl;
        }

        @Nullable
        public final List<Resolution> component15() {
            return this.resolutions;
        }

        @Nullable
        public final Map<String, String> component16() {
            return this.headers;
        }

        @Nullable
        public final List<Language> component17() {
            return this.languages;
        }

        @Nullable
        public final List<Language> component18() {
            return this.languageOptions;
        }

        @Nullable
        /* JADX INFO: renamed from: component19, reason: from getter */
        public final String getHeastr() {
            return this.heastr;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getGenre() {
            return this.genre;
        }

        @Nullable
        /* JADX INFO: renamed from: component20, reason: from getter */
        public final String getUastr() {
            return this.uastr;
        }

        @Nullable
        /* JADX INFO: renamed from: component21, reason: from getter */
        public final String getUaStr() {
            return this.uaStr;
        }

        @Nullable
        /* JADX INFO: renamed from: component22, reason: from getter */
        public final String getHeaderStr() {
            return this.headerStr;
        }

        @Nullable
        /* JADX INFO: renamed from: component23, reason: from getter */
        public final String getSourceType() {
            return this.sourceType;
        }

        @Nullable
        /* JADX INFO: renamed from: component24, reason: from getter */
        public final String getHost() {
            return this.host;
        }

        @Nullable
        /* JADX INFO: renamed from: component25, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        @Nullable
        /* JADX INFO: renamed from: component26, reason: from getter */
        public final String getKey() {
            return this.key;
        }

        @Nullable
        public final List<String> component27() {
            return this.supportedLanguages;
        }

        @Nullable
        /* JADX INFO: renamed from: component28, reason: from getter */
        public final String getSeason() {
            return this.season;
        }

        @Nullable
        /* JADX INFO: renamed from: component29, reason: from getter */
        public final String getEpisode() {
            return this.episode;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getCover() {
            return this.cover;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getImdbRating() {
            return this.imdbRating;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getSeasons() {
            return this.seasons;
        }

        @Nullable
        public final List<VideoSeasonDetail> component7() {
            return this.details;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getSortOrder() {
            return this.sortOrder;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getQuality() {
            return this.quality;
        }

        @NotNull
        public final VideoData copy(@JsonProperty("t") @Nullable String title, @JsonProperty("g") @Nullable String genre, @JsonProperty("y") @Nullable Integer year, @JsonProperty("c") @Nullable String cover, @JsonProperty("i") @Nullable String imdbRating, @JsonProperty("n") @Nullable Integer seasons, @JsonProperty("detail") @Nullable List<VideoSeasonDetail> details, @JsonProperty("so") @Nullable Integer sortOrder, @JsonProperty("q") @Nullable String quality, @JsonProperty("url") @Nullable String url, @JsonProperty("f") @Nullable Integer format, @JsonProperty("clientUrls") @Nullable List<ClientUrl> clientUrls, @JsonProperty("videoUrl") @Nullable String videoUrl, @JsonProperty("playUrl") @Nullable String playUrl, @JsonProperty("resolutions") @Nullable List<Resolution> resolutions, @JsonProperty("headers") @Nullable Map<String, String> headers, @JsonProperty("languages") @Nullable List<Language> languages, @JsonProperty("languageOptions") @Nullable List<Language> languageOptions, @JsonProperty("heastr") @Nullable String heastr, @JsonProperty("uastr") @Nullable String uastr, @JsonProperty("uaStr") @Nullable String uaStr, @JsonProperty("headerStr") @Nullable String headerStr, @JsonProperty("sourceType") @Nullable String sourceType, @JsonProperty("host") @Nullable String host, @JsonProperty("file") @Nullable String file, @JsonProperty("key") @Nullable String key, @JsonProperty("supportedLanguages") @Nullable List<String> supportedLanguages, @JsonProperty("season") @Nullable String season, @JsonProperty("episode") @Nullable String episode) {
            return new VideoData(title, genre, year, cover, imdbRating, seasons, details, sortOrder, quality, url, format, clientUrls, videoUrl, playUrl, resolutions, headers, languages, languageOptions, heastr, uastr, uaStr, headerStr, sourceType, host, file, key, supportedLanguages, season, episode);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoData)) {
                return false;
            }
            VideoData videoData = (VideoData) other;
            return Intrinsics.areEqual(this.title, videoData.title) && Intrinsics.areEqual(this.genre, videoData.genre) && Intrinsics.areEqual(this.year, videoData.year) && Intrinsics.areEqual(this.cover, videoData.cover) && Intrinsics.areEqual(this.imdbRating, videoData.imdbRating) && Intrinsics.areEqual(this.seasons, videoData.seasons) && Intrinsics.areEqual(this.details, videoData.details) && Intrinsics.areEqual(this.sortOrder, videoData.sortOrder) && Intrinsics.areEqual(this.quality, videoData.quality) && Intrinsics.areEqual(this.url, videoData.url) && Intrinsics.areEqual(this.format, videoData.format) && Intrinsics.areEqual(this.clientUrls, videoData.clientUrls) && Intrinsics.areEqual(this.videoUrl, videoData.videoUrl) && Intrinsics.areEqual(this.playUrl, videoData.playUrl) && Intrinsics.areEqual(this.resolutions, videoData.resolutions) && Intrinsics.areEqual(this.headers, videoData.headers) && Intrinsics.areEqual(this.languages, videoData.languages) && Intrinsics.areEqual(this.languageOptions, videoData.languageOptions) && Intrinsics.areEqual(this.heastr, videoData.heastr) && Intrinsics.areEqual(this.uastr, videoData.uastr) && Intrinsics.areEqual(this.uaStr, videoData.uaStr) && Intrinsics.areEqual(this.headerStr, videoData.headerStr) && Intrinsics.areEqual(this.sourceType, videoData.sourceType) && Intrinsics.areEqual(this.host, videoData.host) && Intrinsics.areEqual(this.file, videoData.file) && Intrinsics.areEqual(this.key, videoData.key) && Intrinsics.areEqual(this.supportedLanguages, videoData.supportedLanguages) && Intrinsics.areEqual(this.season, videoData.season) && Intrinsics.areEqual(this.episode, videoData.episode);
        }

        public int hashCode() {
            return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.title == null ? 0 : this.title.hashCode()) * 31) + (this.genre == null ? 0 : this.genre.hashCode())) * 31) + (this.year == null ? 0 : this.year.hashCode())) * 31) + (this.cover == null ? 0 : this.cover.hashCode())) * 31) + (this.imdbRating == null ? 0 : this.imdbRating.hashCode())) * 31) + (this.seasons == null ? 0 : this.seasons.hashCode())) * 31) + (this.details == null ? 0 : this.details.hashCode())) * 31) + (this.sortOrder == null ? 0 : this.sortOrder.hashCode())) * 31) + (this.quality == null ? 0 : this.quality.hashCode())) * 31) + (this.url == null ? 0 : this.url.hashCode())) * 31) + (this.format == null ? 0 : this.format.hashCode())) * 31) + (this.clientUrls == null ? 0 : this.clientUrls.hashCode())) * 31) + (this.videoUrl == null ? 0 : this.videoUrl.hashCode())) * 31) + (this.playUrl == null ? 0 : this.playUrl.hashCode())) * 31) + (this.resolutions == null ? 0 : this.resolutions.hashCode())) * 31) + (this.headers == null ? 0 : this.headers.hashCode())) * 31) + (this.languages == null ? 0 : this.languages.hashCode())) * 31) + (this.languageOptions == null ? 0 : this.languageOptions.hashCode())) * 31) + (this.heastr == null ? 0 : this.heastr.hashCode())) * 31) + (this.uastr == null ? 0 : this.uastr.hashCode())) * 31) + (this.uaStr == null ? 0 : this.uaStr.hashCode())) * 31) + (this.headerStr == null ? 0 : this.headerStr.hashCode())) * 31) + (this.sourceType == null ? 0 : this.sourceType.hashCode())) * 31) + (this.host == null ? 0 : this.host.hashCode())) * 31) + (this.file == null ? 0 : this.file.hashCode())) * 31) + (this.key == null ? 0 : this.key.hashCode())) * 31) + (this.supportedLanguages == null ? 0 : this.supportedLanguages.hashCode())) * 31) + (this.season == null ? 0 : this.season.hashCode())) * 31) + (this.episode != null ? this.episode.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("VideoData(title=").append(this.title).append(", genre=").append(this.genre).append(", year=").append(this.year).append(", cover=").append(this.cover).append(", imdbRating=").append(this.imdbRating).append(", seasons=").append(this.seasons).append(", details=").append(this.details).append(", sortOrder=").append(this.sortOrder).append(", quality=").append(this.quality).append(", url=").append(this.url).append(", format=").append(this.format).append(", clientUrls=");
            sb.append(this.clientUrls).append(", videoUrl=").append(this.videoUrl).append(", playUrl=").append(this.playUrl).append(", resolutions=").append(this.resolutions).append(", headers=").append(this.headers).append(", languages=").append(this.languages).append(", languageOptions=").append(this.languageOptions).append(", heastr=").append(this.heastr).append(", uastr=").append(this.uastr).append(", uaStr=").append(this.uaStr).append(", headerStr=").append(this.headerStr).append(", sourceType=").append(this.sourceType);
            sb.append(", host=").append(this.host).append(", file=").append(this.file).append(", key=").append(this.key).append(", supportedLanguages=").append(this.supportedLanguages).append(", season=").append(this.season).append(", episode=").append(this.episode).append(')');
            return sb.toString();
        }

        public VideoData(@JsonProperty("t") @Nullable String title, @JsonProperty("g") @Nullable String genre, @JsonProperty("y") @Nullable Integer year, @JsonProperty("c") @Nullable String cover, @JsonProperty("i") @Nullable String imdbRating, @JsonProperty("n") @Nullable Integer seasons, @JsonProperty("detail") @Nullable List<VideoSeasonDetail> list, @JsonProperty("so") @Nullable Integer sortOrder, @JsonProperty("q") @Nullable String quality, @JsonProperty("url") @Nullable String url, @JsonProperty("f") @Nullable Integer format, @JsonProperty("clientUrls") @Nullable List<ClientUrl> list2, @JsonProperty("videoUrl") @Nullable String videoUrl, @JsonProperty("playUrl") @Nullable String playUrl, @JsonProperty("resolutions") @Nullable List<Resolution> list3, @JsonProperty("headers") @Nullable Map<String, String> map, @JsonProperty("languages") @Nullable List<Language> list4, @JsonProperty("languageOptions") @Nullable List<Language> list5, @JsonProperty("heastr") @Nullable String heastr, @JsonProperty("uastr") @Nullable String uastr, @JsonProperty("uaStr") @Nullable String uaStr, @JsonProperty("headerStr") @Nullable String headerStr, @JsonProperty("sourceType") @Nullable String sourceType, @JsonProperty("host") @Nullable String host, @JsonProperty("file") @Nullable String file, @JsonProperty("key") @Nullable String key, @JsonProperty("supportedLanguages") @Nullable List<String> list6, @JsonProperty("season") @Nullable String season, @JsonProperty("episode") @Nullable String episode) {
            this.title = title;
            this.genre = genre;
            this.year = year;
            this.cover = cover;
            this.imdbRating = imdbRating;
            this.seasons = seasons;
            this.details = list;
            this.sortOrder = sortOrder;
            this.quality = quality;
            this.url = url;
            this.format = format;
            this.clientUrls = list2;
            this.videoUrl = videoUrl;
            this.playUrl = playUrl;
            this.resolutions = list3;
            this.headers = map;
            this.languages = list4;
            this.languageOptions = list5;
            this.heastr = heastr;
            this.uastr = uastr;
            this.uaStr = uaStr;
            this.headerStr = headerStr;
            this.sourceType = sourceType;
            this.host = host;
            this.file = file;
            this.key = key;
            this.supportedLanguages = list6;
            this.season = season;
            this.episode = episode;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ VideoData(String str, String str2, Integer num, String str3, String str4, Integer num2, List list, Integer num3, String str5, String str6, Integer num4, List list2, String str7, String str8, List list3, Map map, List list4, List list5, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, List list6, String str17, String str18, int i, DefaultConstructorMarker defaultConstructorMarker) {
            String str19 = (i & 1) != 0 ? null : str;
            String str20 = (i & 2) != 0 ? null : str2;
            Integer num5 = (i & 4) != 0 ? null : num;
            String str21 = (i & 8) != 0 ? null : str3;
            String str22 = (i & 16) != 0 ? null : str4;
            Integer num6 = (i & 32) != 0 ? null : num2;
            List list7 = (i & 64) != 0 ? null : list;
            Integer num7 = (i & 128) != 0 ? null : num3;
            String str23 = (i & 256) != 0 ? null : str5;
            String str24 = (i & 512) != 0 ? null : str6;
            Integer num8 = (i & 1024) != 0 ? null : num4;
            List list8 = (i & 2048) != 0 ? null : list2;
            String str25 = (i & 4096) != 0 ? null : str7;
            String str26 = (i & 8192) != 0 ? null : str8;
            List list9 = (i & 16384) != 0 ? null : list3;
            this(str19, str20, num5, str21, str22, num6, list7, num7, str23, str24, num8, list8, str25, str26, list9, (i & 32768) != 0 ? null : map, (i & 65536) != 0 ? null : list4, (i & 131072) != 0 ? null : list5, (i & 262144) != 0 ? null : str9, (i & 524288) != 0 ? null : str10, (i & 1048576) != 0 ? null : str11, (i & 2097152) != 0 ? null : str12, (i & 4194304) != 0 ? null : str13, (i & 8388608) != 0 ? null : str14, (i & 16777216) != 0 ? null : str15, (i & 33554432) != 0 ? null : str16, (i & 67108864) != 0 ? null : list6, (i & 134217728) != 0 ? null : str17, (i & 268435456) != 0 ? null : str18);
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final String getGenre() {
            return this.genre;
        }

        @Nullable
        public final Integer getYear() {
            return this.year;
        }

        @Nullable
        public final String getCover() {
            return this.cover;
        }

        @Nullable
        public final String getImdbRating() {
            return this.imdbRating;
        }

        @Nullable
        public final Integer getSeasons() {
            return this.seasons;
        }

        @Nullable
        public final List<VideoSeasonDetail> getDetails() {
            return this.details;
        }

        @Nullable
        public final Integer getSortOrder() {
            return this.sortOrder;
        }

        @Nullable
        public final String getQuality() {
            return this.quality;
        }

        @Nullable
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        public final Integer getFormat() {
            return this.format;
        }

        @Nullable
        public final List<ClientUrl> getClientUrls() {
            return this.clientUrls;
        }

        @Nullable
        public final String getVideoUrl() {
            return this.videoUrl;
        }

        @Nullable
        public final String getPlayUrl() {
            return this.playUrl;
        }

        @Nullable
        public final List<Resolution> getResolutions() {
            return this.resolutions;
        }

        @Nullable
        public final Map<String, String> getHeaders() {
            return this.headers;
        }

        @Nullable
        public final List<Language> getLanguages() {
            return this.languages;
        }

        @Nullable
        public final List<Language> getLanguageOptions() {
            return this.languageOptions;
        }

        @Nullable
        public final String getHeastr() {
            return this.heastr;
        }

        @Nullable
        public final String getUastr() {
            return this.uastr;
        }

        @Nullable
        public final String getUaStr() {
            return this.uaStr;
        }

        @Nullable
        public final String getHeaderStr() {
            return this.headerStr;
        }

        @Nullable
        public final String getSourceType() {
            return this.sourceType;
        }

        @Nullable
        public final String getHost() {
            return this.host;
        }

        @Nullable
        public final String getFile() {
            return this.file;
        }

        @Nullable
        public final String getKey() {
            return this.key;
        }

        @Nullable
        public final List<String> getSupportedLanguages() {
            return this.supportedLanguages;
        }

        @Nullable
        public final String getSeason() {
            return this.season;
        }

        @Nullable
        public final String getEpisode() {
            return this.episode;
        }
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0003\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0011\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J8\u0010\u0015\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0003\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/cncverse/PikashowProvider$VideoSeasonDetail;", "", "season", "", "year", "", "episodes", "", "Lcom/cncverse/PikashowProvider$VideoEpisode;", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;)V", "getSeason", "()Ljava/lang/String;", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisodes", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;)Lcom/cncverse/PikashowProvider$VideoSeasonDetail;", "equals", "", "other", "hashCode", "toString", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VideoSeasonDetail {

        @JsonProperty("episodes")
        @Nullable
        private final List<VideoEpisode> episodes;

        @JsonProperty("season")
        @Nullable
        private final String season;

        @JsonProperty("year")
        @Nullable
        private final Integer year;

        public VideoSeasonDetail() {
            this(null, null, null, 7, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ VideoSeasonDetail copy$default(VideoSeasonDetail videoSeasonDetail, String str, Integer num, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = videoSeasonDetail.season;
            }
            if ((i & 2) != 0) {
                num = videoSeasonDetail.year;
            }
            if ((i & 4) != 0) {
                list = videoSeasonDetail.episodes;
            }
            return videoSeasonDetail.copy(str, num, list);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSeason() {
            return this.season;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        @Nullable
        public final List<VideoEpisode> component3() {
            return this.episodes;
        }

        @NotNull
        public final VideoSeasonDetail copy(@JsonProperty("season") @Nullable String season, @JsonProperty("year") @Nullable Integer year, @JsonProperty("episodes") @Nullable List<VideoEpisode> episodes) {
            return new VideoSeasonDetail(season, year, episodes);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoSeasonDetail)) {
                return false;
            }
            VideoSeasonDetail videoSeasonDetail = (VideoSeasonDetail) other;
            return Intrinsics.areEqual(this.season, videoSeasonDetail.season) && Intrinsics.areEqual(this.year, videoSeasonDetail.year) && Intrinsics.areEqual(this.episodes, videoSeasonDetail.episodes);
        }

        public int hashCode() {
            return ((((this.season == null ? 0 : this.season.hashCode()) * 31) + (this.year == null ? 0 : this.year.hashCode())) * 31) + (this.episodes != null ? this.episodes.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "VideoSeasonDetail(season=" + this.season + ", year=" + this.year + ", episodes=" + this.episodes + ')';
        }

        public VideoSeasonDetail(@JsonProperty("season") @Nullable String season, @JsonProperty("year") @Nullable Integer year, @JsonProperty("episodes") @Nullable List<VideoEpisode> list) {
            this.season = season;
            this.year = year;
            this.episodes = list;
        }

        public /* synthetic */ VideoSeasonDetail(String str, Integer num, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : list);
        }

        @Nullable
        public final String getSeason() {
            return this.season;
        }

        @Nullable
        public final Integer getYear() {
            return this.year;
        }

        @Nullable
        public final List<VideoEpisode> getEpisodes() {
            return this.episodes;
        }
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/cncverse/PikashowProvider$VideoEpisode;", "", "episode", "", "url", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getEpisode", "()Ljava/lang/String;", "getUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VideoEpisode {

        @JsonProperty("e")
        @Nullable
        private final String episode;

        @JsonProperty("url")
        @Nullable
        private final String url;

        /* JADX WARN: Illegal instructions before constructor call */
        public VideoEpisode() {
            String str = null;
            this(str, str, 3, str);
        }

        public static /* synthetic */ VideoEpisode copy$default(VideoEpisode videoEpisode, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = videoEpisode.episode;
            }
            if ((i & 2) != 0) {
                str2 = videoEpisode.url;
            }
            return videoEpisode.copy(str, str2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEpisode() {
            return this.episode;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @NotNull
        public final VideoEpisode copy(@JsonProperty("e") @Nullable String episode, @JsonProperty("url") @Nullable String url) {
            return new VideoEpisode(episode, url);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoEpisode)) {
                return false;
            }
            VideoEpisode videoEpisode = (VideoEpisode) other;
            return Intrinsics.areEqual(this.episode, videoEpisode.episode) && Intrinsics.areEqual(this.url, videoEpisode.url);
        }

        public int hashCode() {
            return ((this.episode == null ? 0 : this.episode.hashCode()) * 31) + (this.url != null ? this.url.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "VideoEpisode(episode=" + this.episode + ", url=" + this.url + ')';
        }

        public VideoEpisode(@JsonProperty("e") @Nullable String episode, @JsonProperty("url") @Nullable String url) {
            this.episode = episode;
            this.url = url;
        }

        public /* synthetic */ VideoEpisode(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
        }

        @Nullable
        public final String getEpisode() {
            return this.episode;
        }

        @Nullable
        public final String getUrl() {
            return this.url;
        }
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000eJ>\u0010\u0015\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u001c"}, d2 = {"Lcom/cncverse/PikashowProvider$Resolution;", "", "label", "", "url", "width", "", "height", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getLabel", "()Ljava/lang/String;", "getUrl", "getWidth", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getHeight", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/cncverse/PikashowProvider$Resolution;", "equals", "", "other", "hashCode", "toString", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Resolution {

        @JsonProperty("height")
        @Nullable
        private final Integer height;

        @JsonProperty("label")
        @Nullable
        private final String label;

        @JsonProperty("url")
        @Nullable
        private final String url;

        @JsonProperty("width")
        @Nullable
        private final Integer width;

        public Resolution() {
            this(null, null, null, null, 15, null);
        }

        public static /* synthetic */ Resolution copy$default(Resolution resolution, String str, String str2, Integer num, Integer num2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = resolution.label;
            }
            if ((i & 2) != 0) {
                str2 = resolution.url;
            }
            if ((i & 4) != 0) {
                num = resolution.width;
            }
            if ((i & 8) != 0) {
                num2 = resolution.height;
            }
            return resolution.copy(str, str2, num, num2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getWidth() {
            return this.width;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getHeight() {
            return this.height;
        }

        @NotNull
        public final Resolution copy(@JsonProperty("label") @Nullable String label, @JsonProperty("url") @Nullable String url, @JsonProperty("width") @Nullable Integer width, @JsonProperty("height") @Nullable Integer height) {
            return new Resolution(label, url, width, height);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Resolution)) {
                return false;
            }
            Resolution resolution = (Resolution) other;
            return Intrinsics.areEqual(this.label, resolution.label) && Intrinsics.areEqual(this.url, resolution.url) && Intrinsics.areEqual(this.width, resolution.width) && Intrinsics.areEqual(this.height, resolution.height);
        }

        public int hashCode() {
            return ((((((this.label == null ? 0 : this.label.hashCode()) * 31) + (this.url == null ? 0 : this.url.hashCode())) * 31) + (this.width == null ? 0 : this.width.hashCode())) * 31) + (this.height != null ? this.height.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "Resolution(label=" + this.label + ", url=" + this.url + ", width=" + this.width + ", height=" + this.height + ')';
        }

        public Resolution(@JsonProperty("label") @Nullable String label, @JsonProperty("url") @Nullable String url, @JsonProperty("width") @Nullable Integer width, @JsonProperty("height") @Nullable Integer height) {
            this.label = label;
            this.url = url;
            this.width = width;
            this.height = height;
        }

        public /* synthetic */ Resolution(String str, String str2, Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : num2);
        }

        @Nullable
        public final String getLabel() {
            return this.label;
        }

        @Nullable
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        public final Integer getWidth() {
            return this.width;
        }

        @Nullable
        public final Integer getHeight() {
            return this.height;
        }
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0003J3\u0010\u0012\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/cncverse/PikashowProvider$Language;", "", "language", "", "playUrl", "resolutions", "", "Lcom/cncverse/PikashowProvider$Resolution;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getLanguage", "()Ljava/lang/String;", "getPlayUrl", "getResolutions", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Language {

        @JsonProperty("language")
        @Nullable
        private final String language;

        @JsonProperty("playUrl")
        @Nullable
        private final String playUrl;

        @JsonProperty("resolutions")
        @Nullable
        private final List<Resolution> resolutions;

        public Language() {
            this(null, null, null, 7, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Language copy$default(Language language, String str, String str2, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = language.language;
            }
            if ((i & 2) != 0) {
                str2 = language.playUrl;
            }
            if ((i & 4) != 0) {
                list = language.resolutions;
            }
            return language.copy(str, str2, list);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLanguage() {
            return this.language;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPlayUrl() {
            return this.playUrl;
        }

        @Nullable
        public final List<Resolution> component3() {
            return this.resolutions;
        }

        @NotNull
        public final Language copy(@JsonProperty("language") @Nullable String language, @JsonProperty("playUrl") @Nullable String playUrl, @JsonProperty("resolutions") @Nullable List<Resolution> resolutions) {
            return new Language(language, playUrl, resolutions);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Language)) {
                return false;
            }
            Language language = (Language) other;
            return Intrinsics.areEqual(this.language, language.language) && Intrinsics.areEqual(this.playUrl, language.playUrl) && Intrinsics.areEqual(this.resolutions, language.resolutions);
        }

        public int hashCode() {
            return ((((this.language == null ? 0 : this.language.hashCode()) * 31) + (this.playUrl == null ? 0 : this.playUrl.hashCode())) * 31) + (this.resolutions != null ? this.resolutions.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "Language(language=" + this.language + ", playUrl=" + this.playUrl + ", resolutions=" + this.resolutions + ')';
        }

        public Language(@JsonProperty("language") @Nullable String language, @JsonProperty("playUrl") @Nullable String playUrl, @JsonProperty("resolutions") @Nullable List<Resolution> list) {
            this.language = language;
            this.playUrl = playUrl;
            this.resolutions = list;
        }

        public /* synthetic */ Language(String str, String str2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : list);
        }

        @Nullable
        public final String getLanguage() {
            return this.language;
        }

        @Nullable
        public final String getPlayUrl() {
            return this.playUrl;
        }

        @Nullable
        public final List<Resolution> getResolutions() {
            return this.resolutions;
        }
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/cncverse/PikashowProvider$Keys;", "", "file", "", "key", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getFile", "()Ljava/lang/String;", "getKey", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Keys {

        @JsonProperty("file")
        @NotNull
        private final String file;

        @JsonProperty("key")
        @NotNull
        private final String key;

        public static /* synthetic */ Keys copy$default(Keys keys, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = keys.file;
            }
            if ((i & 2) != 0) {
                str2 = keys.key;
            }
            return keys.copy(str, str2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getKey() {
            return this.key;
        }

        @NotNull
        public final Keys copy(@JsonProperty("file") @NotNull String file, @JsonProperty("key") @NotNull String key) {
            return new Keys(file, key);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Keys)) {
                return false;
            }
            Keys keys = (Keys) other;
            return Intrinsics.areEqual(this.file, keys.file) && Intrinsics.areEqual(this.key, keys.key);
        }

        public int hashCode() {
            return (this.file.hashCode() * 31) + this.key.hashCode();
        }

        @NotNull
        public String toString() {
            return "Keys(file=" + this.file + ", key=" + this.key + ')';
        }

        public Keys(@JsonProperty("file") @NotNull String file, @JsonProperty("key") @NotNull String key) {
            this.file = file;
            this.key = key;
        }

        @NotNull
        public final String getFile() {
            return this.file;
        }

        @NotNull
        public final String getKey() {
            return this.key;
        }
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\u000e\b\u0003\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/cncverse/PikashowProvider$Season;", "", "id", "", "folder", "", "Lcom/cncverse/PikashowProvider$HDBVEpisode;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getFolder", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Season {

        @JsonProperty("folder")
        @NotNull
        private final List<HDBVEpisode> folder;

        @JsonProperty("id")
        @NotNull
        private final String id;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Season copy$default(Season season, String str, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = season.id;
            }
            if ((i & 2) != 0) {
                list = season.folder;
            }
            return season.copy(str, list);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        @NotNull
        public final List<HDBVEpisode> component2() {
            return this.folder;
        }

        @NotNull
        public final Season copy(@JsonProperty("id") @NotNull String id, @JsonProperty("folder") @NotNull List<HDBVEpisode> folder) {
            return new Season(id, folder);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Season)) {
                return false;
            }
            Season season = (Season) other;
            return Intrinsics.areEqual(this.id, season.id) && Intrinsics.areEqual(this.folder, season.folder);
        }

        public int hashCode() {
            return (this.id.hashCode() * 31) + this.folder.hashCode();
        }

        @NotNull
        public String toString() {
            return "Season(id=" + this.id + ", folder=" + this.folder + ')';
        }

        public Season(@JsonProperty("id") @NotNull String id, @JsonProperty("folder") @NotNull List<HDBVEpisode> list) {
            this.id = id;
            this.folder = list;
        }

        @NotNull
        public final String getId() {
            return this.id;
        }

        @NotNull
        public final List<HDBVEpisode> getFolder() {
            return this.folder;
        }
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\u000e\b\u0003\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/cncverse/PikashowProvider$HDBVEpisode;", "", "episode", "", "folder", "", "Lcom/cncverse/PikashowProvider$FileData;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "getEpisode", "()Ljava/lang/String;", "getFolder", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class HDBVEpisode {

        @JsonProperty("episode")
        @NotNull
        private final String episode;

        @JsonProperty("folder")
        @NotNull
        private final List<FileData> folder;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ HDBVEpisode copy$default(HDBVEpisode hDBVEpisode, String str, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = hDBVEpisode.episode;
            }
            if ((i & 2) != 0) {
                list = hDBVEpisode.folder;
            }
            return hDBVEpisode.copy(str, list);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEpisode() {
            return this.episode;
        }

        @NotNull
        public final List<FileData> component2() {
            return this.folder;
        }

        @NotNull
        public final HDBVEpisode copy(@JsonProperty("episode") @NotNull String episode, @JsonProperty("folder") @NotNull List<FileData> folder) {
            return new HDBVEpisode(episode, folder);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HDBVEpisode)) {
                return false;
            }
            HDBVEpisode hDBVEpisode = (HDBVEpisode) other;
            return Intrinsics.areEqual(this.episode, hDBVEpisode.episode) && Intrinsics.areEqual(this.folder, hDBVEpisode.folder);
        }

        public int hashCode() {
            return (this.episode.hashCode() * 31) + this.folder.hashCode();
        }

        @NotNull
        public String toString() {
            return "HDBVEpisode(episode=" + this.episode + ", folder=" + this.folder + ')';
        }

        public HDBVEpisode(@JsonProperty("episode") @NotNull String episode, @JsonProperty("folder") @NotNull List<FileData> list) {
            this.episode = episode;
            this.folder = list;
        }

        @NotNull
        public final String getEpisode() {
            return this.episode;
        }

        @NotNull
        public final List<FileData> getFolder() {
            return this.folder;
        }
    }

    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/cncverse/PikashowProvider$FileData;", "", "file", "", "<init>", "(Ljava/lang/String;)V", "getFile", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "PikashowProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class FileData {

        @JsonProperty("file")
        @NotNull
        private final String file;

        public static /* synthetic */ FileData copy$default(FileData fileData, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = fileData.file;
            }
            return fileData.copy(str);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        @NotNull
        public final FileData copy(@JsonProperty("file") @NotNull String file) {
            return new FileData(file);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof FileData) && Intrinsics.areEqual(this.file, ((FileData) other).file);
        }

        public int hashCode() {
            return this.file.hashCode();
        }

        @NotNull
        public String toString() {
            return "FileData(file=" + this.file + ')';
        }

        public FileData(@JsonProperty("file") @NotNull String file) {
            this.file = file;
        }

        @NotNull
        public final String getFile() {
            return this.file;
        }
    }

    static /* synthetic */ Map generateSignature$default(PikashowProvider pikashowProvider, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            l = null;
        }
        return pikashowProvider.generateSignature(l);
    }

    private final Map<String, String> generateSignature(Long timestampMs) throws NoSuchAlgorithmException, InvalidKeyException {
        long timestamp = timestampMs != null ? timestampMs.longValue() : System.currentTimeMillis();
        long timestampSeconds = timestamp / ((long) 1000);
        String timestampStr = String.valueOf(timestampSeconds);
        String message = this.apiKey + ':' + timestampStr;
        Mac mac = Mac.getInstance("HmacSHA256");
        byte[] bytes = this.hmacSecret.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        SecretKeySpec secretKey = new SecretKeySpec(bytes, "HmacSHA256");
        mac.init(secretKey);
        byte[] bytes2 = message.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
        byte[] signature = mac.doFinal(bytes2);
        String signatureHex = ArraysKt.joinToString$default(signature, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.cncverse.PikashowProvider$$ExternalSyntheticLambda15
            public final Object invoke(Object obj) {
                return PikashowProvider.generateSignature$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
        return MapsKt.mapOf(new Pair[]{TuplesKt.to("X-Timestamp", timestampStr), TuplesKt.to("X-API-Key", this.apiKey), TuplesKt.to("X-Signature", signatureHex)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence generateSignature$lambda$0(byte it) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final Map<String, String> getPikashowHeaders() {
        Map sigHeaders = generateSignature$default(this, null, 1, null);
        Object obj = sigHeaders.get("X-API-Key");
        Intrinsics.checkNotNull(obj);
        Object obj2 = sigHeaders.get("X-Signature");
        Intrinsics.checkNotNull(obj2);
        Object obj3 = sigHeaders.get("X-Timestamp");
        Intrinsics.checkNotNull(obj3);
        return MapsKt.mapOf(new Pair[]{TuplesKt.to("Host", "manoda.co"), TuplesKt.to("user-agent", "Pikashow/2509030 (Android 13; Pixel 5; Channel/pikashow; gaid/" + this.gaid + "); Uuid/" + this.deviceUuid), TuplesKt.to("X-API-Key", obj), TuplesKt.to("X-Signature", obj2), TuplesKt.to("X-Timestamp", obj3)});
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    /* JADX WARN: Can't wrap try/catch for region: R(13:89|93|192|94|95|96|144|(0)|154|171|220|20|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(21:22|200|24|25|184|26|27|218|28|29|198|30|31|206|32|33|208|34|35|202|36) */
    /* JADX WARN: Can't wrap try/catch for region: R(23:22|23|200|24|25|184|26|27|218|28|29|198|30|31|206|32|33|208|34|35|202|36|(1:38)(10:39|188|40|41|(4:214|43|44|SW:45)(1:153)|154|171|220|20|(3:176|182|183)(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(6:(1:212)|106|107|190|108|(6:116|(8:118|(5:121|(1:123)(1:124)|(2:127|231)(1:232)|128|119)|230|129|(1:131)|140|144|(0))(1:132)|133|140|144|(0))(4:110|(1:112)(1:113)|114|115)) */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x04b8, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x04bd, code lost:
    
        java.lang.System.out.println((java.lang.Object) ("Error parsing series response: " + r0.getMessage()));
        r0 = kotlin.collections.CollectionsKt.emptyList();
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x055f, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0560, code lost:
    
        r31 = r6;
        r32 = r7;
        r17 = r51;
        r5 = r10;
        r20 = r12;
        r19 = r13;
        r23 = r21;
        r12 = r0;
        r21 = r15;
        r3 = r33;
        r4 = r39;
        r15 = r52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x057c, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x057d, code lost:
    
        r33 = r5;
        r31 = r6;
        r32 = r7;
        r17 = r51;
        r5 = r10;
        r20 = r12;
        r19 = r13;
        r23 = r11;
        r12 = r0;
        r21 = r15;
        r3 = r33;
        r4 = r39;
        r15 = r52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x059d, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x059e, code lost:
    
        r33 = r5;
        r31 = r6;
        r32 = r7;
        r17 = r51;
        r5 = r10;
        r20 = r12;
        r19 = r13;
        r23 = r11;
        r12 = r0;
        r21 = r15;
        r3 = r33;
        r4 = r39;
        r15 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x05c0, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x05c1, code lost:
    
        r33 = r5;
        r31 = r6;
        r32 = r7;
        r17 = r51;
        r5 = r10;
        r20 = r12;
        r19 = r13;
        r23 = r11;
        r12 = r0;
        r21 = r15;
        r3 = r33;
        r4 = r39;
        r15 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x05e5, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x05e6, code lost:
    
        r33 = r5;
        r31 = r6;
        r32 = r7;
        r17 = r51;
        r5 = r10;
        r20 = r12;
        r19 = r13;
        r23 = r11;
        r12 = r0;
        r21 = r15;
        r3 = r33;
        r4 = r9;
        r15 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x060d, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x060e, code lost:
    
        r38 = r4;
        r33 = r5;
        r31 = r6;
        r32 = r7;
        r17 = r51;
        r5 = r10;
        r20 = r12;
        r19 = r13;
        r23 = r11;
        r12 = r0;
        r21 = r15;
        r3 = r33;
        r4 = r9;
        r15 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x01fe, code lost:
    
        if (r10.equals(r7) == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0216, code lost:
    
        if (r10.equals(r6) == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0218, code lost:
    
        r33 = r1;
        r53 = r5;
        r31 = r6;
        r32 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0222, code lost:
    
        r53 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0225, code lost:
    
        r5 = r1.mapper;
        r26 = r4.getText();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0236, code lost:
    
        r31 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x023c, code lost:
    
        r32 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0240, code lost:
    
        r6 = r5.readValue(r26, new com.cncverse.PikashowProvider$getMainPage$lambda$0$$inlined$readValue$2());
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x024b, code lost:
    
        if ((r6 instanceof com.cncverse.PikashowProvider.PikashowMovieResponse) != false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x024d, code lost:
    
        r0 = new java.lang.StringBuilder().append("Deserialized value did not match the specified type; specified ").append(kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(com.cncverse.PikashowProvider.PikashowMovieResponse.class).getQualifiedName()).append("(non-null)").append(" but was ");
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0271, code lost:
    
        if (r6 == null) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0273, code lost:
    
        r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6.getClass()).getQualifiedName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0284, code lost:
    
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0292, code lost:
    
        throw new com.fasterxml.jackson.databind.RuntimeJsonMappingException(r0.append(r4).toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0293, code lost:
    
        r0 = (com.cncverse.PikashowProvider.PikashowMovieResponse) r6;
        r4 = r0.getRecords();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x029e, code lost:
    
        if (r4 == null) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x02a0, code lost:
    
        r4 = r4;
        r6 = new java.util.ArrayList();
        r28 = r4.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x02b9, code lost:
    
        if (r28.hasNext() == false) goto L233;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x02bb, code lost:
    
        r29 = r28.next();
        r35 = (com.cncverse.PikashowProvider.PikashowMovie) r29;
        r37 = r35.getTitle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x02cf, code lost:
    
        if (r37 == null) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x02d1, code lost:
    
        r48 = r0;
        r49 = r4;
        r0 = com.lagradost.cloudstream3.MainAPIKt.newMovieSearchResponse$default(r1, r37, "pikashow:" + r35.getSortOrder() + ':' + r10, com.lagradost.cloudstream3.TvType.Movie, false, new com.cncverse.PikashowProvider$$ExternalSyntheticLambda4(r35, r1), 8, (java.lang.Object) null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0314, code lost:
    
        r48 = r0;
        r49 = r4;
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x031c, code lost:
    
        if (r0 == null) goto L235;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x031e, code lost:
    
        r6.add(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0322, code lost:
    
        r0 = r48;
        r4 = r49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0327, code lost:
    
        r0 = kotlin.collections.CollectionsKt.asReversed((java.util.List) r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0337, code lost:
    
        if (r0 != null) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x033c, code lost:
    
        r0 = kotlin.collections.CollectionsKt.emptyList();
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0341, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0343, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0345, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0346, code lost:
    
        r31 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0348, code lost:
    
        r32 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x034a, code lost:
    
        java.lang.System.out.println((java.lang.Object) ("Error parsing movie response: " + r0.getMessage()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x036a, code lost:
    
        r0 = kotlin.collections.CollectionsKt.emptyList();
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x036b, code lost:
    
        r26 = r0;
        r33 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0371, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0372, code lost:
    
        r3 = r51;
        r5 = r53;
        r36 = r1;
        r23 = r12;
        r12 = r25;
        r4 = r39;
     */
    /* JADX WARN: Removed duplicated region for block: B:146:0x04fa A[Catch: Exception -> 0x050b, TRY_LEAVE, TryCatch #1 {Exception -> 0x050b, blocks: (B:143:0x04e9, B:144:0x04ef, B:146:0x04fa, B:139:0x04bd), top: B:186:0x04e9 }] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x052f  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0697  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x01dd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00fa A[Catch: Exception -> 0x06a5, TRY_LEAVE, TryCatch #18 {Exception -> 0x06a5, blocks: (B:20:0x00f4, B:22:0x00fa), top: B:220:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Type update failed for variable: r9v7 java.util.Map<java.lang.String, java.lang.String>, new type: java.lang.Object
    jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 18501. Try increasing type updates limit count.
    	at jadx.core.dex.visitors.typeinference.TypeUpdateInfo.requestUpdate(TypeUpdateInfo.java:37)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:224)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:86)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyDebugInfo(TypeUpdate.java:76)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:133)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:75)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
     */
    /* JADX WARN: Type update failed for variable: r9v7 java.util.Map<java.lang.String, java.lang.String>, new type: java.lang.Object
    jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 18501. Try increasing type updates limit count.
    	at jadx.core.dex.visitors.typeinference.TypeUpdateInfo.requestUpdate(TypeUpdateInfo.java:37)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:224)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:86)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyDebugInfo(TypeUpdate.java:76)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchDebugInfoByOffset(DebugInfoApplyVisitor.java:107)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:83)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
     */
    /* JADX WARN: Type update failed for variable: r9v8 java.util.Map<java.lang.String, java.lang.String>, new type: java.lang.Object
    jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 18501. Try increasing type updates limit count.
    	at jadx.core.dex.visitors.typeinference.TypeUpdateInfo.requestUpdate(TypeUpdateInfo.java:37)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:224)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:86)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyDebugInfo(TypeUpdate.java:76)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:133)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:75)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
     */
    /* JADX WARN: Type update failed for variable: r9v8 java.util.Map<java.lang.String, java.lang.String>, new type: java.lang.Object
    jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 18501. Try increasing type updates limit count.
    	at jadx.core.dex.visitors.typeinference.TypeUpdateInfo.requestUpdate(TypeUpdateInfo.java:37)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:224)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:86)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyDebugInfo(TypeUpdate.java:76)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchDebugInfoByOffset(DebugInfoApplyVisitor.java:107)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:83)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:170:0x065c -> B:171:0x066a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x01b7 -> B:188:0x01d1). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getMainPage(int r51, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.MainPageRequest r52, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.HomePageResponse> r53) throws com.fasterxml.jackson.databind.RuntimeJsonMappingException {
        /*
            Method dump skipped, instruction units count: 1850
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.PikashowProvider.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$0$0$0$0(PikashowSeries $series, TvSeriesSearchResponse $this$newTvSeriesSearchResponse) {
        $this$newTvSeriesSearchResponse.setPosterUrl($series.getCover());
        $this$newTvSeriesSearchResponse.setYear($series.getYear());
        $this$newTvSeriesSearchResponse.setQuality(SearchQuality.HD);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$0$1$0$0(PikashowMovie $movie, PikashowProvider this$0, MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl($movie.getCover());
        $this$newMovieSearchResponse.setYear($movie.getYear());
        $this$newMovieSearchResponse.setQuality(this$0.getQualityFromString($movie.getQuality()));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$1(MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl((String) null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final SearchQuality getQualityFromString(String qualityString) {
        String upperCase;
        if (qualityString != null) {
            upperCase = qualityString.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        } else {
            upperCase = null;
        }
        if (upperCase == null) {
            return null;
        }
        switch (upperCase.hashCode()) {
            case -577968313:
                if (!upperCase.equals("TELECINE")) {
                    return null;
                }
                return SearchQuality.Telecine;
            case -577476283:
                if (!upperCase.equals("TELESYNC")) {
                    return null;
                }
                return SearchQuality.Telesync;
            case -402211300:
                if (!upperCase.equals("WORKPRINT")) {
                    return null;
                }
                return SearchQuality.WorkPrint;
            case 1687:
                if (!upperCase.equals("4K")) {
                    return null;
                }
                return SearchQuality.HD;
            case 2300:
                if (!upperCase.equals("HD")) {
                    return null;
                }
                return SearchQuality.HD;
            case 2671:
                if (!upperCase.equals("TC")) {
                    return null;
                }
                return SearchQuality.Telecine;
            case 2687:
                if (!upperCase.equals("TS")) {
                    return null;
                }
                return SearchQuality.Telesync;
            case 2777:
                if (!upperCase.equals("WP")) {
                    return null;
                }
                return SearchQuality.WorkPrint;
            case 66479:
                if (!upperCase.equals("CAM")) {
                    return null;
                }
                return SearchQuality.Cam;
            case 69570:
                if (!upperCase.equals("FHD")) {
                    return null;
                }
                return SearchQuality.HD;
            case 1688123:
                if (!upperCase.equals("720P")) {
                    return null;
                }
                return SearchQuality.HD;
            case 46737881:
                if (!upperCase.equals("1080P")) {
                    return null;
                }
                return SearchQuality.HD;
            case 47689271:
                if (!upperCase.equals("2160P")) {
                    return null;
                }
                return SearchQuality.HD;
            case 68585779:
                if (upperCase.equals("HDCAM")) {
                    return SearchQuality.HdCam;
                }
                return null;
            case 1980557034:
                if (!upperCase.equals("CAMRIP")) {
                    return null;
                }
                return SearchQuality.Cam;
            default:
                return null;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    /* JADX WARN: Can't wrap try/catch for region: R(10:100|104|233|105|106|182|202|257|24|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(10:155|156|169|263|172|182|202|257|24|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(23:26|27|251|28|29|243|30|31|259|32|33|229|34|35|255|36|37|247|38|39|215|40|(1:42)(10:43|241|44|45|(4:261|47|48|SW:49)(1:181)|182|202|257|24|(3:205|211|212)(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(6:231|117|118|245|119|(2:127|(4:129|(4:132|(7:134|135|239|136|(3:223|138|(4:140|(7:142|143|249|144|145|(1:147)|(0)(2:154|268))(1:150)|151|(0))(0))(1:157)|159|267)(2:163|266)|164|130)|265|165)(1:166))(4:121|(1:123)(1:124)|125|126)) */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x03b2, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x03b4, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x03b5, code lost:
    
        r34 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x03b7, code lost:
    
        r35 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x03b9, code lost:
    
        java.lang.System.out.println((java.lang.Object) ("Error parsing movie search response: " + r0.getMessage()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x03d5, code lost:
    
        r36 = r2;
        r48 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x03dd, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x03de, code lost:
    
        r25 = r1;
        r36 = r2;
        r48 = r8;
        r20 = r12;
        r12 = r23;
        r24 = r28;
        r22 = r29;
        r4 = r31;
        r6 = r34;
        r23 = r38;
        r5 = r40;
        r21 = r41;
        r2 = r50;
        r1 = r51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0597, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x0598, code lost:
    
        r48 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x05c2, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x05c3, code lost:
    
        r2 = r50;
        r25 = r1;
        r20 = r12;
        r12 = r23;
        r24 = r28;
        r22 = r29;
        r4 = r31;
        r6 = r34;
        r23 = r38;
        r5 = r40;
        r21 = r41;
        r1 = r51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x0675, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x0676, code lost:
    
        r35 = r7;
        r48 = r8;
        r1 = r50;
        r25 = r2;
        r20 = r12;
        r11 = r19;
        r22 = r14;
        r24 = r21;
        r4 = r33;
        r23 = r38;
        r21 = r41;
        r2 = r51;
        r12 = r0;
        r5 = r40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x0696, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x0697, code lost:
    
        r35 = r7;
        r48 = r8;
        r21 = r11;
        r1 = r50;
        r25 = r2;
        r20 = r12;
        r11 = r19;
        r22 = r14;
        r24 = r21;
        r23 = r38;
        r21 = r41;
        r2 = r51;
        r12 = r0;
        r5 = r40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x06b9, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x06ba, code lost:
    
        r38 = r1;
        r35 = r7;
        r48 = r8;
        r21 = r11;
        r1 = r50;
        r25 = r2;
        r20 = r12;
        r11 = r19;
        r22 = r14;
        r24 = r21;
        r23 = r38;
        r21 = r41;
        r2 = r51;
        r12 = r0;
        r5 = r40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x06de, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x06df, code lost:
    
        r38 = r1;
        r35 = r7;
        r48 = r8;
        r21 = r11;
        r1 = r50;
        r25 = r2;
        r20 = r12;
        r11 = r19;
        r22 = r14;
        r24 = r21;
        r23 = r38;
        r21 = r41;
        r2 = r51;
        r12 = r0;
        r5 = r40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x0705, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x0706, code lost:
    
        r35 = r7;
        r48 = r8;
        r21 = r11;
        r41 = r13;
        r13 = r19;
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x0718, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x0719, code lost:
    
        r35 = r7;
        r48 = r8;
        r21 = r11;
        r41 = r13;
        r13 = r19;
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x0728, code lost:
    
        r38 = r1;
        r1 = r50;
        r25 = r2;
        r20 = r12;
        r11 = r13;
        r22 = r14;
        r24 = r21;
        r23 = r38;
        r21 = r41;
        r2 = r51;
        r12 = r5;
        r5 = r40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x0740, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x0741, code lost:
    
        r35 = r7;
        r48 = r8;
        r21 = r11;
        r38 = r1;
        r1 = r50;
        r25 = r2;
        r20 = r12;
        r11 = r19;
        r22 = r14;
        r24 = r21;
        r23 = r38;
        r21 = r13;
        r2 = r51;
        r12 = r0;
        r5 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0220, code lost:
    
        if (r11.equals(r8) == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0249, code lost:
    
        if (r11.equals(r7) == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x024b, code lost:
    
        r36 = r2;
        r34 = r6;
        r35 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0254, code lost:
    
        r5 = r2.mapper;
        r20 = r5.getText();
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0265, code lost:
    
        r34 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x026b, code lost:
    
        r35 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x026f, code lost:
    
        r6 = r5.readValue(r20, new com.cncverse.PikashowProvider$search$lambda$0$$inlined$readValue$2());
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x027a, code lost:
    
        if ((r6 instanceof com.cncverse.PikashowProvider.PikashowMovieResponse) != false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x027c, code lost:
    
        r0 = new java.lang.StringBuilder().append("Deserialized value did not match the specified type; specified ").append(kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(com.cncverse.PikashowProvider.PikashowMovieResponse.class).getQualifiedName()).append("(non-null)").append(" but was ");
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x02a0, code lost:
    
        if (r6 == null) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x02a2, code lost:
    
        r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6.getClass()).getQualifiedName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x02b3, code lost:
    
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x02c1, code lost:
    
        throw new com.fasterxml.jackson.databind.RuntimeJsonMappingException(r0.append(r5).toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x02c2, code lost:
    
        r0 = (com.cncverse.PikashowProvider.PikashowMovieResponse) r6;
        r4 = r0.getRecords();
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x02cb, code lost:
    
        if (r4 == null) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x02cd, code lost:
    
        r4 = r4;
        r5 = 0;
        r6 = r4.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x02d8, code lost:
    
        if (r6.hasNext() == false) goto L269;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x02da, code lost:
    
        r20 = (com.cncverse.PikashowProvider.PikashowMovie) r6.next();
        r21 = r20.getTitle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x02ea, code lost:
    
        if (r21 == null) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x02ec, code lost:
    
        r43 = r0;
        r44 = r4;
        r0 = r21.toLowerCase(java.util.Locale.ROOT);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "toLowerCase(...)");
        r45 = r5;
        r46 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0314, code lost:
    
        if (kotlin.text.StringsKt.contains$default(r0, r38, false, 2, (java.lang.Object) null) != false) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0316, code lost:
    
        r0 = r20.getGenre();
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x031a, code lost:
    
        if (r0 == null) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x031c, code lost:
    
        r0 = r0.toLowerCase(java.util.Locale.ROOT);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "toLowerCase(...)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0325, code lost:
    
        if (r0 == null) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0335, code lost:
    
        if (kotlin.text.StringsKt.contains$default(r0, r38, false, 2, (java.lang.Object) null) != true) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0337, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0339, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x033a, code lost:
    
        if (r0 == false) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0340, code lost:
    
        r1.add(com.lagradost.cloudstream3.MainAPIKt.newMovieSearchResponse$default(r2, r21, "pikashow:" + r20.getSortOrder() + ':' + r11, r23, false, new com.cncverse.PikashowProvider$$ExternalSyntheticLambda7(r20, r2), 8, (java.lang.Object) null));
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x037e, code lost:
    
        r43 = r0;
        r44 = r4;
        r45 = r5;
        r46 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x038a, code lost:
    
        r0 = r43;
        r4 = r44;
        r5 = r45;
        r6 = r46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0398, code lost:
    
        r36 = r2;
        r48 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x03a6, code lost:
    
        r36 = r2;
        r48 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x03b0, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0515  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x051d  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x062a  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x07b8  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x01fb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x011c A[Catch: Exception -> 0x07c7, TRY_LEAVE, TryCatch #22 {Exception -> 0x07c7, blocks: (B:24:0x0116, B:26:0x011c), top: B:257:0x0116 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:201:0x078f -> B:202:0x0799). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x01d7 -> B:241:0x01ef). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object search(@org.jetbrains.annotations.NotNull java.lang.String r50, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.SearchResponse>> r51) throws com.fasterxml.jackson.databind.RuntimeJsonMappingException {
        /*
            Method dump skipped, instruction units count: 2102
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.PikashowProvider.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit search$lambda$0$0$0$0(PikashowSeries $series, TvSeriesSearchResponse $this$newTvSeriesSearchResponse) {
        $this$newTvSeriesSearchResponse.setPosterUrl($series.getCover());
        $this$newTvSeriesSearchResponse.setYear($series.getYear());
        $this$newTvSeriesSearchResponse.setQuality(SearchQuality.HD);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit search$lambda$0$1$0$0(PikashowMovie $movie, PikashowProvider this$0, MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl($movie.getCover());
        $this$newMovieSearchResponse.setYear($movie.getYear());
        $this$newMovieSearchResponse.setQuality(this$0.getQualityFromString($movie.getQuality()));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x01d0, code lost:
    
        if (r15.equals("hollywood") == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x01e0, code lost:
    
        if (r15.equals("bollywood") == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x01e2, code lost:
    
        r27 = r7;
        r30 = r14;
        r26 = r15;
        r8 = r15;
        r2 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x01f5, code lost:
    
        r0 = getMainUrl() + "/v1/api/videos";
        r10 = kotlin.collections.MapsKt.mapOf(new kotlin.Pair[]{kotlin.TuplesKt.to("type", r15), kotlin.TuplesKt.to("channel", "pikashow")});
        r6 = com.lagradost.cloudstream3.MainActivityKt.getApp();
        r12.L$0 = r32;
        r12.L$1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r24);
        r12.L$2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7);
        r12.L$3 = r14;
        r12.L$4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r15);
        r12.L$5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r15);
        r12.L$6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0);
        r12.L$7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10);
        r12.label = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0251, code lost:
    
        r26 = r15;
        r5 = "Deserialized value did not match the specified type; specified ";
        r4 = r6;
        r3 = " but was ";
        r27 = r7;
        r2 = 200;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0282, code lost:
    
        r6 = com.lagradost.nicehttp.Requests.get$default(r6, r0, r15, (java.lang.String) null, r10, (java.util.Map) null, false, 0, (java.util.concurrent.TimeUnit) null, 30, (okhttp3.Interceptor) null, false, (com.lagradost.nicehttp.ResponseParser) null, r12, 3828, (java.lang.Object) null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0286, code lost:
    
        r12 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0288, code lost:
    
        if (r6 != r4) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x028a, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x028b, code lost:
    
        r14 = r0;
        r15 = r15;
        r13 = r10;
        r8 = r32;
        r7 = r14;
        r0 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x03e4, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x03e5, code lost:
    
        r2 = null;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:108:0x04b6 A[Catch: Exception -> 0x06e3, TryCatch #11 {Exception -> 0x06e3, blocks: (B:88:0x03dd, B:53:0x0293, B:55:0x029d, B:57:0x02b8, B:59:0x02de, B:61:0x02f0, B:62:0x02fd, B:63:0x02fe, B:65:0x0309, B:66:0x030f, B:68:0x0315, B:72:0x032d, B:75:0x0334, B:78:0x0341, B:106:0x04ac, B:108:0x04b6, B:110:0x04cf, B:112:0x04f5, B:114:0x0507, B:115:0x0514, B:116:0x0515, B:118:0x0520, B:119:0x0526, B:121:0x052c, B:125:0x0540, B:128:0x0547, B:135:0x0574, B:137:0x057a, B:143:0x0589), top: B:204:0x0032 }] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x06d4  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001e  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull java.lang.String r32, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r33) throws com.fasterxml.jackson.databind.RuntimeJsonMappingException {
        /*
            Method dump skipped, instruction units count: 1860
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.PikashowProvider.load(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit load$lambda$1$0$0(int $episodeNum, int $seasonNumber, Episode $this$newEpisode) {
        $this$newEpisode.setName("Episode " + $episodeNum);
        $this$newEpisode.setSeason(Integer.valueOf($seasonNumber));
        $this$newEpisode.setEpisode(Integer.valueOf($episodeNum));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:106:0x05a8  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0733 A[Catch: Exception -> 0x0b4e, TRY_LEAVE, TryCatch #2 {Exception -> 0x0b4e, blocks: (B:131:0x0727, B:133:0x0733, B:192:0x08e2, B:194:0x08e6, B:196:0x08ea), top: B:258:0x0727 }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0780 A[Catch: Exception -> 0x0824, TryCatch #18 {Exception -> 0x0824, blocks: (B:136:0x074a, B:142:0x0761, B:144:0x0780, B:146:0x07a8, B:148:0x07bb, B:149:0x07c8, B:150:0x07c9, B:152:0x07d4, B:153:0x07da, B:155:0x07e0, B:160:0x0802, B:163:0x080b, B:139:0x0753, B:167:0x0827, B:170:0x0833, B:172:0x0852, B:174:0x0878, B:176:0x0889, B:177:0x0896, B:178:0x0897, B:180:0x08a2, B:181:0x08a8, B:183:0x08ae, B:188:0x08cc, B:191:0x08d5), top: B:288:0x0741 }] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x07c9 A[Catch: Exception -> 0x0824, TryCatch #18 {Exception -> 0x0824, blocks: (B:136:0x074a, B:142:0x0761, B:144:0x0780, B:146:0x07a8, B:148:0x07bb, B:149:0x07c8, B:150:0x07c9, B:152:0x07d4, B:153:0x07da, B:155:0x07e0, B:160:0x0802, B:163:0x080b, B:139:0x0753, B:167:0x0827, B:170:0x0833, B:172:0x0852, B:174:0x0878, B:176:0x0889, B:177:0x0896, B:178:0x0897, B:180:0x08a2, B:181:0x08a8, B:183:0x08ae, B:188:0x08cc, B:191:0x08d5), top: B:288:0x0741 }] */
    /* JADX WARN: Removed duplicated region for block: B:194:0x08e6 A[Catch: Exception -> 0x0b4e, TryCatch #2 {Exception -> 0x0b4e, blocks: (B:131:0x0727, B:133:0x0733, B:192:0x08e2, B:194:0x08e6, B:196:0x08ea), top: B:258:0x0727 }] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0a08 A[Catch: Exception -> 0x0b29, TryCatch #5 {Exception -> 0x0b29, blocks: (B:203:0x09fa, B:205:0x0a08, B:207:0x0a2a, B:209:0x0a50, B:211:0x0a63, B:212:0x0a70, B:213:0x0a71, B:215:0x0a7f), top: B:264:0x09fa }] */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0b1f  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0b45  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0481  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r46, boolean r47, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r48, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r49, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r50) throws com.fasterxml.jackson.databind.RuntimeJsonMappingException {
        /*
            Method dump skipped, instruction units count: 3042
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.PikashowProvider.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadLinks$lambda$0$0(Context $_ctx) {
        Toast.makeText($_ctx, "⚠️(Opening ads) Subscription expired. If you have renewed your subscription, please re-verify it in Subscription Manager.", 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    /* JADX WARN: Path cross not found for [B:107:0x03ed, B:134:0x043d], limit reached: 333 */
    /* JADX WARN: Path cross not found for [B:109:0x03f3, B:130:0x0437], limit reached: 333 */
    /* JADX WARN: Path cross not found for [B:134:0x043d, B:107:0x03ed], limit reached: 333 */
    /* JADX WARN: Path cross not found for [B:84:0x03a6, B:105:0x03ea], limit reached: 333 */
    /* JADX WARN: Removed duplicated region for block: B:107:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x043b  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0441 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x048d  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0566  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x063b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:210:0x063c  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x069f A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Removed duplicated region for block: B:222:0x06a9  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x06ca  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x07eb  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0805  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x081c  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0822  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x083c  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0874  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0a01  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0a44  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0490 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:243:0x079e -> B:244:0x07b2). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:245:0x07ca -> B:246:0x07e0). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:258:0x084c -> B:259:0x086e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:289:0x0995 -> B:290:0x09b7). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:291:0x09cf -> B:292:0x09ed). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object addVideoLinksToCallback(com.cncverse.PikashowProvider.VideoData r40, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r41, java.lang.String r42, kotlin.coroutines.Continuation<? super kotlin.Unit> r43) throws com.fasterxml.jackson.databind.RuntimeJsonMappingException {
        /*
            Method dump skipped, instruction units count: 2658
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.PikashowProvider.addVideoLinksToCallback(com.cncverse.PikashowProvider$VideoData, kotlin.jvm.functions.Function1, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.cncverse.PikashowProvider$addVideoLinksToCallback$8, reason: invalid class name */
    /* JADX INFO: compiled from: PikashowProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PikashowProvider$addVideoLinksToCallback$8", f = "PikashowProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass8 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ Map<String, String> $finalHeaders;
        final /* synthetic */ String $urlOrigin;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass8(String str, Map<String, String> map, Continuation<? super AnonymousClass8> continuation) {
            super(2, continuation);
            this.$urlOrigin = str;
            this.$finalHeaders = map;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass8 = new AnonymousClass8(this.$urlOrigin, this.$finalHeaders, continuation);
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
                    $this$newExtractorLink.setReferer(this.$urlOrigin);
                    $this$newExtractorLink.setQuality(Qualities.P720.getValue());
                    $this$newExtractorLink.setHeaders(this.$finalHeaders);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    /* JADX WARN: Removed duplicated region for block: B:107:0x068a A[Catch: Exception -> 0x069b, TRY_LEAVE, TryCatch #4 {Exception -> 0x069b, blocks: (B:105:0x0680, B:107:0x068a, B:101:0x065f, B:59:0x0479), top: B:137:0x0479 }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x06a7  */
    /* JADX WARN: Removed duplicated region for block: B:153:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0292 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0293 A[Catch: Exception -> 0x06c3, TryCatch #9 {Exception -> 0x06c3, blocks: (B:35:0x0288, B:38:0x0293, B:41:0x02aa, B:43:0x02cd, B:55:0x0345), top: B:147:0x0288 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0493 A[Catch: Exception -> 0x06ae, TRY_LEAVE, TryCatch #1 {Exception -> 0x06ae, blocks: (B:64:0x0489, B:66:0x0493, B:73:0x04b3, B:85:0x0550, B:88:0x0559, B:90:0x0565, B:92:0x056b, B:94:0x0573, B:96:0x0579, B:99:0x0592, B:72:0x04af), top: B:131:0x0489 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object parseHDBVPlayerUrl(java.lang.String r49, kotlin.coroutines.Continuation<? super java.lang.String> r50) throws com.fasterxml.jackson.databind.RuntimeJsonMappingException {
        /*
            Method dump skipped, instruction units count: 1794
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.PikashowProvider.parseHDBVPlayerUrl(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String extractImdbIdFromUrl(String url) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = url.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            byte[] hash = messageDigest.digest(bytes);
            return StringsKt.take(ArraysKt.joinToString$default(hash, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.cncverse.PikashowProvider$$ExternalSyntheticLambda13
                public final Object invoke(Object obj) {
                    return PikashowProvider.extractImdbIdFromUrl$lambda$0(((Byte) obj).byteValue());
                }
            }, 30, (Object) null), 10);
        } catch (Exception e) {
            return "default";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence extractImdbIdFromUrl$lambda$0(byte it) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fallbackToDirectUrls(com.cncverse.PikashowProvider.VideoData r17, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r18, java.lang.String r19, java.util.Map<java.lang.String, java.lang.String> r20, kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            Method dump skipped, instruction units count: 502
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.PikashowProvider.fallbackToDirectUrls(com.cncverse.PikashowProvider$VideoData, kotlin.jvm.functions.Function1, java.lang.String, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0020, code lost:
    
        if (r0.equals("2160P") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0029, code lost:
    
        if (r0.equals("1080P") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0032, code lost:
    
        if (r0.equals("720P") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003b, code lost:
    
        if (r0.equals("480P") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0044, code lost:
    
        if (r0.equals("FHD") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0054, code lost:
    
        if (r0.equals("SD") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0064, code lost:
    
        if (r0.equals("HD") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0074, code lost:
    
        if (r0.equals("4K") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:?, code lost:
    
        return com.lagradost.cloudstream3.utils.Qualities.P1080.getValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:?, code lost:
    
        return com.lagradost.cloudstream3.utils.Qualities.P480.getValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:?, code lost:
    
        return com.lagradost.cloudstream3.utils.Qualities.P720.getValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:?, code lost:
    
        return com.lagradost.cloudstream3.utils.Qualities.P2160.getValue();
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int getQualityValue(java.lang.String r3) {
        /*
            r2 = this;
            if (r3 == 0) goto Le
            java.util.Locale r0 = java.util.Locale.ROOT
            java.lang.String r0 = r3.toUpperCase(r0)
            java.lang.String r1 = "toUpperCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            goto Lf
        Le:
            r0 = 0
        Lf:
            if (r0 == 0) goto L7e
            int r1 = r0.hashCode()
            switch(r1) {
                case 1687: goto L6e;
                case 2300: goto L5e;
                case 2641: goto L4e;
                case 69570: goto L3e;
                case 1604516: goto L35;
                case 1688123: goto L2c;
                case 46737881: goto L23;
                case 47689271: goto L1a;
                default: goto L18;
            }
        L18:
            goto L7e
        L1a:
            java.lang.String r1 = "2160P"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L77
            goto L7e
        L23:
            java.lang.String r1 = "1080P"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L47
            goto L7e
        L2c:
            java.lang.String r1 = "720P"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L67
            goto L7e
        L35:
            java.lang.String r1 = "480P"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L57
            goto L7e
        L3e:
            java.lang.String r1 = "FHD"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L47
            goto L7e
        L47:
            com.lagradost.cloudstream3.utils.Qualities r0 = com.lagradost.cloudstream3.utils.Qualities.P1080
            int r0 = r0.getValue()
            goto L84
        L4e:
            java.lang.String r1 = "SD"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L57
            goto L7e
        L57:
            com.lagradost.cloudstream3.utils.Qualities r0 = com.lagradost.cloudstream3.utils.Qualities.P480
            int r0 = r0.getValue()
            goto L84
        L5e:
            java.lang.String r1 = "HD"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L67
            goto L7e
        L67:
            com.lagradost.cloudstream3.utils.Qualities r0 = com.lagradost.cloudstream3.utils.Qualities.P720
            int r0 = r0.getValue()
            goto L84
        L6e:
            java.lang.String r1 = "4K"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L77
            goto L7e
        L77:
            com.lagradost.cloudstream3.utils.Qualities r0 = com.lagradost.cloudstream3.utils.Qualities.P2160
            int r0 = r0.getValue()
            goto L84
        L7e:
            com.lagradost.cloudstream3.utils.Qualities r0 = com.lagradost.cloudstream3.utils.Qualities.Unknown
            int r0 = r0.getValue()
        L84:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.PikashowProvider.getQualityValue(java.lang.String):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final int getQualityValueFromLabel(String label) {
        String lowerCase;
        if (label != null) {
            lowerCase = label.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        } else {
            lowerCase = null;
        }
        if (lowerCase != null) {
            switch (lowerCase.hashCode()) {
                case 1572835:
                    if (lowerCase.equals("360p")) {
                        return Qualities.P360.getValue();
                    }
                    break;
                case 1604548:
                    if (lowerCase.equals("480p")) {
                        return Qualities.P480.getValue();
                    }
                    break;
                case 1688155:
                    if (lowerCase.equals("720p")) {
                        return Qualities.P720.getValue();
                    }
                    break;
                case 46737913:
                    if (lowerCase.equals("1080p")) {
                        return Qualities.P1080.getValue();
                    }
                    break;
                case 1544803905:
                    if (lowerCase.equals("default")) {
                        return Qualities.P720.getValue();
                    }
                    break;
            }
        }
        return Qualities.Unknown.getValue();
    }


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */
}
