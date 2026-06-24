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
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.ExtensionsKt;
import com.lagradost.cloudstream3.ActorData;
import com.lagradost.cloudstream3.Episode;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.MovieLoadResponse;
import com.lagradost.cloudstream3.MovieSearchResponse;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.ShowStatus;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: CastleTvProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nCastleTvProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CastleTvProvider.kt\ncom/cncverse/CastleTvProvider\n+ 2 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1069:1\n116#2:1070\n54#2:1071\n117#2:1072\n61#2,8:1073\n71#2:1082\n116#2:1083\n54#2:1084\n117#2:1085\n61#2,8:1086\n71#2:1095\n116#2:1096\n54#2:1097\n117#2:1098\n61#2,8:1099\n71#2:1108\n116#2:1137\n54#2:1138\n117#2:1139\n61#2,8:1140\n71#2:1149\n116#2:1164\n54#2:1165\n117#2:1166\n61#2,8:1167\n71#2:1176\n116#2:1181\n54#2:1182\n117#2:1183\n61#2,8:1184\n71#2:1193\n116#2:1199\n54#2:1200\n117#2:1201\n61#2,8:1202\n71#2:1211\n116#2:1230\n54#2:1231\n117#2:1232\n61#2,8:1233\n71#2:1242\n116#2:1245\n54#2:1246\n117#2:1247\n61#2,8:1248\n71#2:1257\n1#3:1081\n1#3:1094\n1#3:1107\n1#3:1131\n1#3:1134\n1#3:1148\n1#3:1161\n1#3:1175\n1#3:1192\n1#3:1210\n1#3:1212\n1#3:1227\n1#3:1241\n1#3:1256\n1#3:1260\n1642#4,10:1109\n1915#4:1119\n1642#4,10:1120\n1915#4:1130\n1916#4:1132\n1652#4:1133\n1916#4:1135\n1652#4:1136\n1642#4,10:1150\n1915#4:1160\n1916#4:1162\n1652#4:1163\n1586#4:1177\n1661#4,3:1178\n1915#4,2:1194\n1924#4,3:1196\n1807#4,3:1213\n1642#4,10:1216\n1915#4:1226\n1916#4:1228\n1652#4:1229\n1915#4,2:1243\n1915#4,2:1258\n*S KotlinDebug\n*F\n+ 1 CastleTvProvider.kt\ncom/cncverse/CastleTvProvider\n*L\n275#1:1070\n275#1:1071\n275#1:1072\n275#1:1073,8\n275#1:1082\n331#1:1083\n331#1:1084\n331#1:1085\n331#1:1086,8\n331#1:1095\n345#1:1096\n345#1:1097\n345#1:1098\n345#1:1099,8\n345#1:1108\n403#1:1137\n403#1:1138\n403#1:1139\n403#1:1140,8\n403#1:1149\n454#1:1164\n454#1:1165\n454#1:1166\n454#1:1167,8\n454#1:1176\n498#1:1181\n498#1:1182\n498#1:1183\n498#1:1184,8\n498#1:1193\n616#1:1199\n616#1:1200\n616#1:1201\n616#1:1202,8\n616#1:1211\n673#1:1230\n673#1:1231\n673#1:1232\n673#1:1233,8\n673#1:1242\n765#1:1245\n765#1:1246\n765#1:1247\n765#1:1248,8\n765#1:1257\n275#1:1081\n331#1:1094\n345#1:1107\n350#1:1131\n348#1:1134\n403#1:1148\n406#1:1161\n454#1:1175\n498#1:1192\n616#1:1210\n638#1:1227\n673#1:1241\n765#1:1256\n348#1:1109,10\n348#1:1119\n350#1:1120,10\n350#1:1130\n350#1:1132\n350#1:1133\n348#1:1135\n348#1:1136\n406#1:1150,10\n406#1:1160\n406#1:1162\n406#1:1163\n466#1:1177\n466#1:1178,3\n500#1:1194,2\n518#1:1196,3\n634#1:1213,3\n638#1:1216,10\n638#1:1226\n638#1:1228\n638#1:1229\n706#1:1243,2\n798#1:1258,2\n*E\n"})
public final class CastleTvProvider extends MainAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull

    @Nullable
    private static Context context;

    @NotNull
    private final List<MainPageData> mainPage;

    @NotNull
    private final ObjectMapper mapper;

    @NotNull
    private String mainUrl = "https://api.hlowb.com";

    @NotNull
    private String name = "Castle TV (Use VLC)";
    private final boolean hasMainPage = true;

    @NotNull
    private String lang = "ta";

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(new TvType[]{TvType.Movie, TvType.TvSeries});

    @NotNull
    private final String keySupFixx = "T!BgJB";

    /* JADX INFO: renamed from: com.cncverse.CastleTvProvider$getMainPage$1, reason: invalid class name */
    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CastleTvProvider", f = "CastleTvProvider.kt", i = {0, 0, 1, 1, 1, 1}, l = {327, 329}, m = "getMainPage", n = {"request", "page", "request", "securityKey", "url", "page"}, nl = {328, 330}, s = {"L$0", "I$0", "L$0", "L$1", "L$2", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
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
            return CastleTvProvider.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.CastleTvProvider$getSecurityKey$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CastleTvProvider", f = "CastleTvProvider.kt", i = {0}, l = {274}, m = "getSecurityKey", n = {"url"}, nl = {275}, s = {"L$0"}, v = 2)
    static final class C00001 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00001(Continuation<? super C00001> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CastleTvProvider.this.getSecurityKey((Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.CastleTvProvider$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CastleTvProvider", f = "CastleTvProvider.kt", i = {0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, l = {439, 442, 492, 530, 554}, m = "load", n = {"url", "movieId", "url", "movieId", "securityKey", "detailsUrl", "url", "movieId", "securityKey", "detailsUrl", "response", "encryptedData", "decryptedJson", "detailsResponse", "details", "title", "posterUrl", "backgroundPosterUrl", "plot", "year", "rating", "tags", "actors", "recommendations", "allEpisodes", "season", "seasonId", "seasonUrl", "isSeriesLike", "seasonNumber", "url", "movieId", "securityKey", "detailsUrl", "response", "encryptedData", "decryptedJson", "detailsResponse", "details", "title", "posterUrl", "backgroundPosterUrl", "plot", "year", "rating", "tags", "actors", "recommendations", "allEpisodes", "isSeriesLike", "url", "movieId", "securityKey", "detailsUrl", "response", "encryptedData", "decryptedJson", "detailsResponse", "details", "title", "posterUrl", "backgroundPosterUrl", "plot", "year", "rating", "tags", "actors", "recommendations", "episode", "isSeriesLike"}, nl = {440, 443, 493, 553, 573}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$17", "L$18", "L$20", "L$21", "L$22", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$17", "L$18", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$17", "L$18", "I$0"}, v = 2)
    static final class C00011 extends ContinuationImpl {
        int I$0;
        int I$1;
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
        Object L$20;
        Object L$21;
        Object L$22;
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
            return CastleTvProvider.this.load(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.CastleTvProvider$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CastleTvProvider", f = "CastleTvProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, l = {612, 614, 659, 684, 709, 749, 776, 801}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "parts", "movieId", "episodeId", "isCasting", "data", "subtitleCallback", "callback", "parts", "movieId", "episodeId", "securityKey", "detailsUrl", "isCasting", "data", "subtitleCallback", "callback", "parts", "movieId", "episodeId", "securityKey", "detailsUrl", "detailsResponse", "detailsDecrypted", "details", "episode", "availableTracks", "resolutions", "firstTrack", "allLanguageNames", "videoUrl", "postBody", "isCasting", "videoLoaded", "hasIndividualVideo", "languageId", "resolution", "data", "subtitleCallback", "callback", "parts", "movieId", "episodeId", "securityKey", "detailsUrl", "detailsResponse", "detailsDecrypted", "details", "episode", "availableTracks", "resolutions", "firstTrack", "allLanguageNames", "videoUrl", "postBody", "videoResponse", "encryptedData", "decryptedJson", "videoData", "qualityName", "isCasting", "videoLoaded", "hasIndividualVideo", "languageId", "resolution", "data", "subtitleCallback", "callback", "parts", "movieId", "episodeId", "securityKey", "detailsUrl", "detailsResponse", "detailsDecrypted", "details", "episode", "availableTracks", "resolutions", "firstTrack", "allLanguageNames", "videoUrl", "postBody", "videoResponse", "encryptedData", "decryptedJson", "videoData", "qualityName", "$this$forEach$iv", "element$iv", "subtitle", "isCasting", "videoLoaded", "hasIndividualVideo", "languageId", "resolution", "$i$f$forEach", "$i$a$-forEach-CastleTvProvider$loadLinks$4", "data", "subtitleCallback", "callback", "parts", "movieId", "episodeId", "securityKey", "detailsUrl", "detailsResponse", "detailsDecrypted", "details", "episode", "availableTracks", "resolutions", "track", "languageName", "videoUrl", "postBody", "isCasting", "videoLoaded", "hasIndividualVideo", "languageId", "resolution", "data", "subtitleCallback", "callback", "parts", "movieId", "episodeId", "securityKey", "detailsUrl", "detailsResponse", "detailsDecrypted", "details", "episode", "availableTracks", "resolutions", "track", "languageName", "videoUrl", "postBody", "videoResponse", "encryptedData", "decryptedJson", "videoData", "qualityName", "isCasting", "videoLoaded", "hasIndividualVideo", "languageId", "resolution", "data", "subtitleCallback", "callback", "parts", "movieId", "episodeId", "securityKey", "detailsUrl", "detailsResponse", "detailsDecrypted", "details", "episode", "availableTracks", "resolutions", "track", "languageName", "videoUrl", "postBody", "videoResponse", "encryptedData", "decryptedJson", "videoData", "qualityName", "$this$forEach$iv", "element$iv", "subtitle", "isCasting", "videoLoaded", "hasIndividualVideo", "languageId", "resolution", "$i$f$forEach", "$i$a$-forEach-CastleTvProvider$loadLinks$6"}, nl = {613, 615, 664, 683, 708, 754, 775, 800}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$17", "L$18", "Z$0", "I$0", "I$1", "I$2", "I$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$17", "L$18", "L$19", "L$20", "L$21", "L$22", "L$23", "Z$0", "I$0", "I$1", "I$2", "I$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$17", "L$18", "L$19", "L$20", "L$21", "L$22", "L$23", "L$24", "L$26", "L$27", "Z$0", "I$0", "I$1", "I$2", "I$3", "I$4", "I$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$15", "L$16", "L$18", "L$19", "Z$0", "I$0", "I$1", "I$2", "I$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$15", "L$16", "L$18", "L$19", "L$20", "L$21", "L$22", "L$23", "L$24", "Z$0", "I$0", "I$1", "I$2", "I$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$15", "L$16", "L$18", "L$19", "L$20", "L$21", "L$22", "L$23", "L$24", "L$25", "L$27", "L$28", "Z$0", "I$0", "I$1", "I$2", "I$3", "I$4", "I$5"}, v = 2)
    static final class C00021 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
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
        Object L$20;
        Object L$21;
        Object L$22;
        Object L$23;
        Object L$24;
        Object L$25;
        Object L$26;
        Object L$27;
        Object L$28;
        Object L$29;
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

        C00021(Continuation<? super C00021> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CastleTvProvider.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.CastleTvProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CastleTvProvider", f = "CastleTvProvider.kt", i = {0, 1, 1, 1}, l = {388, 391}, m = "search", n = {"query", "query", "securityKey", "searchUrl"}, nl = {389, 392}, s = {"L$0", "L$0", "L$1", "L$2"}, v = 2)
    static final class C00041 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C00041(Continuation<? super C00041> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CastleTvProvider.this.search(null, (Continuation) this);
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/cncverse/CastleTvProvider$Companion;", "", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "OMG10", "", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return CastleTvProvider.context;
        }

        public final void setContext(@Nullable Context context) {
            CastleTvProvider.context = context;
        }
    }

    public CastleTvProvider() {
        ObjectMapper $this$mapper_u24lambda_u240 = ExtensionsKt.jacksonObjectMapper();
        $this$mapper_u24lambda_u240.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper = $this$mapper_u24lambda_u240;
        this.mainPage = MainAPIKt.mainPageOf(new Pair[]{TuplesKt.to("1", "Home")});
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

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/cncverse/CastleTvProvider$CastleApiResponse;", "", "code", "", "msg", "", "data", "<init>", "(ILjava/lang/String;Ljava/lang/String;)V", "getCode", "()I", "getMsg", "()Ljava/lang/String;", "getData", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class CastleApiResponse {
        private final int code;

        @Nullable
        private final String data;

        @NotNull
        private final String msg;

        public static /* synthetic */ CastleApiResponse copy$default(CastleApiResponse castleApiResponse, int i, String str, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = castleApiResponse.code;
            }
            if ((i2 & 2) != 0) {
                str = castleApiResponse.msg;
            }
            if ((i2 & 4) != 0) {
                str2 = castleApiResponse.data;
            }
            return castleApiResponse.copy(i, str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCode() {
            return this.code;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMsg() {
            return this.msg;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getData() {
            return this.data;
        }

        @NotNull
        public final CastleApiResponse copy(int code, @NotNull String msg, @Nullable String data) {
            return new CastleApiResponse(code, msg, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CastleApiResponse)) {
                return false;
            }
            CastleApiResponse castleApiResponse = (CastleApiResponse) other;
            return this.code == castleApiResponse.code && Intrinsics.areEqual(this.msg, castleApiResponse.msg) && Intrinsics.areEqual(this.data, castleApiResponse.data);
        }

        public int hashCode() {
            return (((this.code * 31) + this.msg.hashCode()) * 31) + (this.data == null ? 0 : this.data.hashCode());
        }

        @NotNull
        public String toString() {
            return "CastleApiResponse(code=" + this.code + ", msg=" + this.msg + ", data=" + this.data + ')';
        }

        public CastleApiResponse(int code, @NotNull String msg, @Nullable String data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        public /* synthetic */ CastleApiResponse(int i, String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, str, (i2 & 4) != 0 ? null : str2);
        }

        public final int getCode() {
            return this.code;
        }

        @NotNull
        public final String getMsg() {
            return this.msg;
        }

        @Nullable
        public final String getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/cncverse/CastleTvProvider$SecurityKeyResponse;", "", "code", "", "msg", "", "data", "<init>", "(ILjava/lang/String;Ljava/lang/String;)V", "getCode", "()I", "getMsg", "()Ljava/lang/String;", "getData", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SecurityKeyResponse {
        private final int code;

        @NotNull
        private final String data;

        @NotNull
        private final String msg;

        public static /* synthetic */ SecurityKeyResponse copy$default(SecurityKeyResponse securityKeyResponse, int i, String str, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = securityKeyResponse.code;
            }
            if ((i2 & 2) != 0) {
                str = securityKeyResponse.msg;
            }
            if ((i2 & 4) != 0) {
                str2 = securityKeyResponse.data;
            }
            return securityKeyResponse.copy(i, str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCode() {
            return this.code;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMsg() {
            return this.msg;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getData() {
            return this.data;
        }

        @NotNull
        public final SecurityKeyResponse copy(int code, @NotNull String msg, @NotNull String data) {
            return new SecurityKeyResponse(code, msg, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SecurityKeyResponse)) {
                return false;
            }
            SecurityKeyResponse securityKeyResponse = (SecurityKeyResponse) other;
            return this.code == securityKeyResponse.code && Intrinsics.areEqual(this.msg, securityKeyResponse.msg) && Intrinsics.areEqual(this.data, securityKeyResponse.data);
        }

        public int hashCode() {
            return (((this.code * 31) + this.msg.hashCode()) * 31) + this.data.hashCode();
        }

        @NotNull
        public String toString() {
            return "SecurityKeyResponse(code=" + this.code + ", msg=" + this.msg + ", data=" + this.data + ')';
        }

        public SecurityKeyResponse(int code, @NotNull String msg, @NotNull String data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        public final int getCode() {
            return this.code;
        }

        @NotNull
        public final String getMsg() {
            return this.msg;
        }

        @NotNull
        public final String getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/cncverse/CastleTvProvider$DecryptedResponse;", "", "code", "", "msg", "", "data", "Lcom/cncverse/CastleTvProvider$HomePageData;", "<init>", "(ILjava/lang/String;Lcom/cncverse/CastleTvProvider$HomePageData;)V", "getCode", "()I", "getMsg", "()Ljava/lang/String;", "getData", "()Lcom/cncverse/CastleTvProvider$HomePageData;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DecryptedResponse {
        private final int code;

        @NotNull
        private final HomePageData data;

        @NotNull
        private final String msg;

        public static /* synthetic */ DecryptedResponse copy$default(DecryptedResponse decryptedResponse, int i, String str, HomePageData homePageData, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = decryptedResponse.code;
            }
            if ((i2 & 2) != 0) {
                str = decryptedResponse.msg;
            }
            if ((i2 & 4) != 0) {
                homePageData = decryptedResponse.data;
            }
            return decryptedResponse.copy(i, str, homePageData);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCode() {
            return this.code;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMsg() {
            return this.msg;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final HomePageData getData() {
            return this.data;
        }

        @NotNull
        public final DecryptedResponse copy(int code, @NotNull String msg, @NotNull HomePageData data) {
            return new DecryptedResponse(code, msg, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DecryptedResponse)) {
                return false;
            }
            DecryptedResponse decryptedResponse = (DecryptedResponse) other;
            return this.code == decryptedResponse.code && Intrinsics.areEqual(this.msg, decryptedResponse.msg) && Intrinsics.areEqual(this.data, decryptedResponse.data);
        }

        public int hashCode() {
            return (((this.code * 31) + this.msg.hashCode()) * 31) + this.data.hashCode();
        }

        @NotNull
        public String toString() {
            return "DecryptedResponse(code=" + this.code + ", msg=" + this.msg + ", data=" + this.data + ')';
        }

        public DecryptedResponse(int code, @NotNull String msg, @NotNull HomePageData data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        public final int getCode() {
            return this.code;
        }

        @NotNull
        public final String getMsg() {
            return this.msg;
        }

        @NotNull
        public final HomePageData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u0011\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003JP\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\rR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0010\u0010\rR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0011\u0010\rR\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006!"}, d2 = {"Lcom/cncverse/CastleTvProvider$HomePageData;", "", "page", "", "pages", "size", "total", "rows", "", "Lcom/cncverse/CastleTvProvider$HomePageRow;", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;)V", "getPage", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPages", "getSize", "getTotal", "getRows", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;)Lcom/cncverse/CastleTvProvider$HomePageData;", "equals", "", "other", "hashCode", "toString", "", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class HomePageData {

        @Nullable
        private final Integer page;

        @Nullable
        private final Integer pages;

        @Nullable
        private final List<HomePageRow> rows;

        @Nullable
        private final Integer size;

        @Nullable
        private final Integer total;

        public HomePageData() {
            this(null, null, null, null, null, 31, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ HomePageData copy$default(HomePageData homePageData, Integer num, Integer num2, Integer num3, Integer num4, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                num = homePageData.page;
            }
            if ((i & 2) != 0) {
                num2 = homePageData.pages;
            }
            if ((i & 4) != 0) {
                num3 = homePageData.size;
            }
            if ((i & 8) != 0) {
                num4 = homePageData.total;
            }
            if ((i & 16) != 0) {
                list = homePageData.rows;
            }
            List list2 = list;
            Integer num5 = num3;
            return homePageData.copy(num, num2, num5, num4, list2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getPage() {
            return this.page;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getPages() {
            return this.pages;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getSize() {
            return this.size;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getTotal() {
            return this.total;
        }

        @Nullable
        public final List<HomePageRow> component5() {
            return this.rows;
        }

        @NotNull
        public final HomePageData copy(@Nullable Integer page, @Nullable Integer pages, @Nullable Integer size, @Nullable Integer total, @Nullable List<HomePageRow> rows) {
            return new HomePageData(page, pages, size, total, rows);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HomePageData)) {
                return false;
            }
            HomePageData homePageData = (HomePageData) other;
            return Intrinsics.areEqual(this.page, homePageData.page) && Intrinsics.areEqual(this.pages, homePageData.pages) && Intrinsics.areEqual(this.size, homePageData.size) && Intrinsics.areEqual(this.total, homePageData.total) && Intrinsics.areEqual(this.rows, homePageData.rows);
        }

        public int hashCode() {
            return ((((((((this.page == null ? 0 : this.page.hashCode()) * 31) + (this.pages == null ? 0 : this.pages.hashCode())) * 31) + (this.size == null ? 0 : this.size.hashCode())) * 31) + (this.total == null ? 0 : this.total.hashCode())) * 31) + (this.rows != null ? this.rows.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "HomePageData(page=" + this.page + ", pages=" + this.pages + ", size=" + this.size + ", total=" + this.total + ", rows=" + this.rows + ')';
        }

        public HomePageData(@Nullable Integer page, @Nullable Integer pages, @Nullable Integer size, @Nullable Integer total, @Nullable List<HomePageRow> list) {
            this.page = page;
            this.pages = pages;
            this.size = size;
            this.total = total;
            this.rows = list;
        }

        public /* synthetic */ HomePageData(Integer num, Integer num2, Integer num3, Integer num4, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : num3, (i & 8) != 0 ? null : num4, (i & 16) != 0 ? null : list);
        }

        @Nullable
        public final Integer getPage() {
            return this.page;
        }

        @Nullable
        public final Integer getPages() {
            return this.pages;
        }

        @Nullable
        public final Integer getSize() {
            return this.size;
        }

        @Nullable
        public final Integer getTotal() {
            return this.total;
        }

        @Nullable
        public final List<HomePageRow> getRows() {
            return this.rows;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001By\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010$\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010%\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010&\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010'\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0019J\u000b\u0010(\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010)\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eHÆ\u0003J\u0080\u0001\u0010*\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eHÆ\u0001¢\u0006\u0002\u0010+J\u0014\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010/\u001a\u00020\bHÖ\u0081\u0004J\n\u00100\u001a\u00020\u0005HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001b\u0010\u0019R\u0015\u0010\n\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001c\u0010\u0019R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001d\u0010\u0019R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u00061"}, d2 = {"Lcom/cncverse/CastleTvProvider$HomePageRow;", "", "id", "", "name", "", "coverImage", "coverImageHeight", "", "coverImageWidth", "type", "redirectType", "briefIntroduction", "contents", "", "Lcom/cncverse/CastleTvProvider$ContentItem;", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getName", "()Ljava/lang/String;", "getCoverImage", "getCoverImageHeight", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCoverImageWidth", "getType", "getRedirectType", "getBriefIntroduction", "getContents", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;)Lcom/cncverse/CastleTvProvider$HomePageRow;", "equals", "", "other", "hashCode", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class HomePageRow {

        @Nullable
        private final String briefIntroduction;

        @Nullable
        private final List<ContentItem> contents;

        @Nullable
        private final String coverImage;

        @Nullable
        private final Integer coverImageHeight;

        @Nullable
        private final Integer coverImageWidth;

        @Nullable
        private final Long id;

        @Nullable
        private final String name;

        @Nullable
        private final Integer redirectType;

        @Nullable
        private final Integer type;

        public HomePageRow() {
            this(null, null, null, null, null, null, null, null, null, 511, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ HomePageRow copy$default(HomePageRow homePageRow, Long l, String str, String str2, Integer num, Integer num2, Integer num3, Integer num4, String str3, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                l = homePageRow.id;
            }
            if ((i & 2) != 0) {
                str = homePageRow.name;
            }
            if ((i & 4) != 0) {
                str2 = homePageRow.coverImage;
            }
            if ((i & 8) != 0) {
                num = homePageRow.coverImageHeight;
            }
            if ((i & 16) != 0) {
                num2 = homePageRow.coverImageWidth;
            }
            if ((i & 32) != 0) {
                num3 = homePageRow.type;
            }
            if ((i & 64) != 0) {
                num4 = homePageRow.redirectType;
            }
            if ((i & 128) != 0) {
                str3 = homePageRow.briefIntroduction;
            }
            if ((i & 256) != 0) {
                list = homePageRow.contents;
            }
            String str4 = str3;
            List list2 = list;
            Integer num5 = num3;
            Integer num6 = num4;
            Integer num7 = num2;
            String str5 = str2;
            return homePageRow.copy(l, str, str5, num, num7, num5, num6, str4, list2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Long getId() {
            return this.id;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCoverImage() {
            return this.coverImage;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getCoverImageHeight() {
            return this.coverImageHeight;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getCoverImageWidth() {
            return this.coverImageWidth;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getType() {
            return this.type;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getRedirectType() {
            return this.redirectType;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getBriefIntroduction() {
            return this.briefIntroduction;
        }

        @Nullable
        public final List<ContentItem> component9() {
            return this.contents;
        }

        @NotNull
        public final HomePageRow copy(@Nullable Long id, @Nullable String name, @Nullable String coverImage, @Nullable Integer coverImageHeight, @Nullable Integer coverImageWidth, @Nullable Integer type, @Nullable Integer redirectType, @Nullable String briefIntroduction, @Nullable List<ContentItem> contents) {
            return new HomePageRow(id, name, coverImage, coverImageHeight, coverImageWidth, type, redirectType, briefIntroduction, contents);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HomePageRow)) {
                return false;
            }
            HomePageRow homePageRow = (HomePageRow) other;
            return Intrinsics.areEqual(this.id, homePageRow.id) && Intrinsics.areEqual(this.name, homePageRow.name) && Intrinsics.areEqual(this.coverImage, homePageRow.coverImage) && Intrinsics.areEqual(this.coverImageHeight, homePageRow.coverImageHeight) && Intrinsics.areEqual(this.coverImageWidth, homePageRow.coverImageWidth) && Intrinsics.areEqual(this.type, homePageRow.type) && Intrinsics.areEqual(this.redirectType, homePageRow.redirectType) && Intrinsics.areEqual(this.briefIntroduction, homePageRow.briefIntroduction) && Intrinsics.areEqual(this.contents, homePageRow.contents);
        }

        public int hashCode() {
            return ((((((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.coverImage == null ? 0 : this.coverImage.hashCode())) * 31) + (this.coverImageHeight == null ? 0 : this.coverImageHeight.hashCode())) * 31) + (this.coverImageWidth == null ? 0 : this.coverImageWidth.hashCode())) * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31) + (this.redirectType == null ? 0 : this.redirectType.hashCode())) * 31) + (this.briefIntroduction == null ? 0 : this.briefIntroduction.hashCode())) * 31) + (this.contents != null ? this.contents.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "HomePageRow(id=" + this.id + ", name=" + this.name + ", coverImage=" + this.coverImage + ", coverImageHeight=" + this.coverImageHeight + ", coverImageWidth=" + this.coverImageWidth + ", type=" + this.type + ", redirectType=" + this.redirectType + ", briefIntroduction=" + this.briefIntroduction + ", contents=" + this.contents + ')';
        }

        public HomePageRow(@Nullable Long id, @Nullable String name, @Nullable String coverImage, @Nullable Integer coverImageHeight, @Nullable Integer coverImageWidth, @Nullable Integer type, @Nullable Integer redirectType, @Nullable String briefIntroduction, @Nullable List<ContentItem> list) {
            this.id = id;
            this.name = name;
            this.coverImage = coverImage;
            this.coverImageHeight = coverImageHeight;
            this.coverImageWidth = coverImageWidth;
            this.type = type;
            this.redirectType = redirectType;
            this.briefIntroduction = briefIntroduction;
            this.contents = list;
        }

        public /* synthetic */ HomePageRow(Long l, String str, String str2, Integer num, Integer num2, Integer num3, Integer num4, String str3, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : l, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : num2, (i & 32) != 0 ? null : num3, (i & 64) != 0 ? null : num4, (i & 128) != 0 ? null : str3, (i & 256) != 0 ? null : list);
        }

        @Nullable
        public final Long getId() {
            return this.id;
        }

        @Nullable
        public final String getName() {
            return this.name;
        }

        @Nullable
        public final String getCoverImage() {
            return this.coverImage;
        }

        @Nullable
        public final Integer getCoverImageHeight() {
            return this.coverImageHeight;
        }

        @Nullable
        public final Integer getCoverImageWidth() {
            return this.coverImageWidth;
        }

        @Nullable
        public final Integer getType() {
            return this.type;
        }

        @Nullable
        public final Integer getRedirectType() {
            return this.redirectType;
        }

        @Nullable
        public final String getBriefIntroduction() {
            return this.briefIntroduction;
        }

        @Nullable
        public final List<ContentItem> getContents() {
            return this.contents;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\bE\b\u0086\b\u0018\u00002\u00020\u0001B\u0083\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0012\u0012\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010?\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010@\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010%J\u0010\u0010A\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010B\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010)J\u0010\u0010C\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010%J\u0010\u0010D\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010E\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010F\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010/J\u0011\u0010G\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0012HÆ\u0003J\u0011\u0010H\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0012HÆ\u0003J\u0010\u0010I\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010J\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010%J\u000b\u0010K\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010L\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010%J\u0010\u0010M\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010N\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010O\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010%J\u000b\u0010P\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u008a\u0002\u0010Q\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00122\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÆ\u0001¢\u0006\u0002\u0010RJ\u0014\u0010S\u001a\u00020\u00102\b\u0010T\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010U\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010V\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010#\u001a\u0004\b!\u0010\"R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010&\u001a\u0004\b$\u0010%R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010#\u001a\u0004\b'\u0010\"R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010*\u001a\u0004\b(\u0010)R\u0015\u0010\f\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010&\u001a\u0004\b+\u0010%R\u0015\u0010\r\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010#\u001a\u0004\b,\u0010\"R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010#\u001a\u0004\b-\u0010\"R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\n\n\u0002\u00100\u001a\u0004\b.\u0010/R\u0019\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0019\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b3\u00102R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010#\u001a\u0004\b4\u0010\"R\u0015\u0010\u0015\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010&\u001a\u0004\b5\u0010%R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u001fR\u0015\u0010\u0017\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010&\u001a\u0004\b7\u0010%R\u0015\u0010\u0018\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010#\u001a\u0004\b8\u0010\"R\u0015\u0010\u0019\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010#\u001a\u0004\b9\u0010\"R\u0015\u0010\u001a\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010&\u001a\u0004\b:\u0010%R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<¨\u0006W"}, d2 = {"Lcom/cncverse/CastleTvProvider$ContentItem;", "", "title", "", "coverImage", "redirectType", "", "redirectId", "", "movieType", "score", "", "publishTime", "heat", "order", "unlockPlayback", "", "languages", "", "excludeChannelIds", "memberLevel", "standardExpireTime", "indiaResolutionLabel", "standardNewExpireTime", "countdownHourNew", "countdownHour", "serverTime", "woolUser", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Object;)V", "getTitle", "()Ljava/lang/String;", "getCoverImage", "getRedirectType", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getRedirectId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getMovieType", "getScore", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getPublishTime", "getHeat", "getOrder", "getUnlockPlayback", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getLanguages", "()Ljava/util/List;", "getExcludeChannelIds", "getMemberLevel", "getStandardExpireTime", "getIndiaResolutionLabel", "getStandardNewExpireTime", "getCountdownHourNew", "getCountdownHour", "getServerTime", "getWoolUser", "()Ljava/lang/Object;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Object;)Lcom/cncverse/CastleTvProvider$ContentItem;", "equals", "other", "hashCode", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ContentItem {

        @Nullable
        private final Integer countdownHour;

        @Nullable
        private final Integer countdownHourNew;

        @Nullable
        private final String coverImage;

        @Nullable
        private final List<String> excludeChannelIds;

        @Nullable
        private final Integer heat;

        @Nullable
        private final String indiaResolutionLabel;

        @Nullable
        private final List<String> languages;

        @Nullable
        private final Integer memberLevel;

        @Nullable
        private final Integer movieType;

        @Nullable
        private final Integer order;

        @Nullable
        private final Long publishTime;

        @Nullable
        private final Long redirectId;

        @Nullable
        private final Integer redirectType;

        @Nullable
        private final Double score;

        @Nullable
        private final Long serverTime;

        @Nullable
        private final Long standardExpireTime;

        @Nullable
        private final Long standardNewExpireTime;

        @Nullable
        private final String title;

        @Nullable
        private final Boolean unlockPlayback;

        @Nullable
        private final Object woolUser;

        public ContentItem() {
            this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1048575, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ContentItem copy$default(ContentItem contentItem, String str, String str2, Integer num, Long l, Integer num2, Double d, Long l2, Integer num3, Integer num4, Boolean bool, List list, List list2, Integer num5, Long l3, String str3, Long l4, Integer num6, Integer num7, Long l5, Object obj, int i, Object obj2) {
            Object obj3;
            Long l6;
            String str4 = (i & 1) != 0 ? contentItem.title : str;
            String str5 = (i & 2) != 0 ? contentItem.coverImage : str2;
            Integer num8 = (i & 4) != 0 ? contentItem.redirectType : num;
            Long l7 = (i & 8) != 0 ? contentItem.redirectId : l;
            Integer num9 = (i & 16) != 0 ? contentItem.movieType : num2;
            Double d2 = (i & 32) != 0 ? contentItem.score : d;
            Long l8 = (i & 64) != 0 ? contentItem.publishTime : l2;
            Integer num10 = (i & 128) != 0 ? contentItem.heat : num3;
            Integer num11 = (i & 256) != 0 ? contentItem.order : num4;
            Boolean bool2 = (i & 512) != 0 ? contentItem.unlockPlayback : bool;
            List list3 = (i & 1024) != 0 ? contentItem.languages : list;
            List list4 = (i & 2048) != 0 ? contentItem.excludeChannelIds : list2;
            Integer num12 = (i & 4096) != 0 ? contentItem.memberLevel : num5;
            Long l9 = (i & 8192) != 0 ? contentItem.standardExpireTime : l3;
            String str6 = str4;
            String str7 = (i & 16384) != 0 ? contentItem.indiaResolutionLabel : str3;
            Long l10 = (i & 32768) != 0 ? contentItem.standardNewExpireTime : l4;
            Integer num13 = (i & 65536) != 0 ? contentItem.countdownHourNew : num6;
            Integer num14 = (i & 131072) != 0 ? contentItem.countdownHour : num7;
            Long l11 = (i & 262144) != 0 ? contentItem.serverTime : l5;
            if ((i & 524288) != 0) {
                l6 = l11;
                obj3 = contentItem.woolUser;
            } else {
                obj3 = obj;
                l6 = l11;
            }
            return contentItem.copy(str6, str5, num8, l7, num9, d2, l8, num10, num11, bool2, list3, list4, num12, l9, str7, l10, num13, num14, l6, obj3);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Boolean getUnlockPlayback() {
            return this.unlockPlayback;
        }

        @Nullable
        public final List<String> component11() {
            return this.languages;
        }

        @Nullable
        public final List<String> component12() {
            return this.excludeChannelIds;
        }

        @Nullable
        /* JADX INFO: renamed from: component13, reason: from getter */
        public final Integer getMemberLevel() {
            return this.memberLevel;
        }

        @Nullable
        /* JADX INFO: renamed from: component14, reason: from getter */
        public final Long getStandardExpireTime() {
            return this.standardExpireTime;
        }

        @Nullable
        /* JADX INFO: renamed from: component15, reason: from getter */
        public final String getIndiaResolutionLabel() {
            return this.indiaResolutionLabel;
        }

        @Nullable
        /* JADX INFO: renamed from: component16, reason: from getter */
        public final Long getStandardNewExpireTime() {
            return this.standardNewExpireTime;
        }

        @Nullable
        /* JADX INFO: renamed from: component17, reason: from getter */
        public final Integer getCountdownHourNew() {
            return this.countdownHourNew;
        }

        @Nullable
        /* JADX INFO: renamed from: component18, reason: from getter */
        public final Integer getCountdownHour() {
            return this.countdownHour;
        }

        @Nullable
        /* JADX INFO: renamed from: component19, reason: from getter */
        public final Long getServerTime() {
            return this.serverTime;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCoverImage() {
            return this.coverImage;
        }

        @Nullable
        /* JADX INFO: renamed from: component20, reason: from getter */
        public final Object getWoolUser() {
            return this.woolUser;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getRedirectType() {
            return this.redirectType;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Long getRedirectId() {
            return this.redirectId;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getMovieType() {
            return this.movieType;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Double getScore() {
            return this.score;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Long getPublishTime() {
            return this.publishTime;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getHeat() {
            return this.heat;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Integer getOrder() {
            return this.order;
        }

        @NotNull
        public final ContentItem copy(@Nullable String title, @Nullable String coverImage, @Nullable Integer redirectType, @Nullable Long redirectId, @Nullable Integer movieType, @Nullable Double score, @Nullable Long publishTime, @Nullable Integer heat, @Nullable Integer order, @Nullable Boolean unlockPlayback, @Nullable List<String> languages, @Nullable List<String> excludeChannelIds, @Nullable Integer memberLevel, @Nullable Long standardExpireTime, @Nullable String indiaResolutionLabel, @Nullable Long standardNewExpireTime, @Nullable Integer countdownHourNew, @Nullable Integer countdownHour, @Nullable Long serverTime, @Nullable Object woolUser) {
            return new ContentItem(title, coverImage, redirectType, redirectId, movieType, score, publishTime, heat, order, unlockPlayback, languages, excludeChannelIds, memberLevel, standardExpireTime, indiaResolutionLabel, standardNewExpireTime, countdownHourNew, countdownHour, serverTime, woolUser);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ContentItem)) {
                return false;
            }
            ContentItem contentItem = (ContentItem) other;
            return Intrinsics.areEqual(this.title, contentItem.title) && Intrinsics.areEqual(this.coverImage, contentItem.coverImage) && Intrinsics.areEqual(this.redirectType, contentItem.redirectType) && Intrinsics.areEqual(this.redirectId, contentItem.redirectId) && Intrinsics.areEqual(this.movieType, contentItem.movieType) && Intrinsics.areEqual(this.score, contentItem.score) && Intrinsics.areEqual(this.publishTime, contentItem.publishTime) && Intrinsics.areEqual(this.heat, contentItem.heat) && Intrinsics.areEqual(this.order, contentItem.order) && Intrinsics.areEqual(this.unlockPlayback, contentItem.unlockPlayback) && Intrinsics.areEqual(this.languages, contentItem.languages) && Intrinsics.areEqual(this.excludeChannelIds, contentItem.excludeChannelIds) && Intrinsics.areEqual(this.memberLevel, contentItem.memberLevel) && Intrinsics.areEqual(this.standardExpireTime, contentItem.standardExpireTime) && Intrinsics.areEqual(this.indiaResolutionLabel, contentItem.indiaResolutionLabel) && Intrinsics.areEqual(this.standardNewExpireTime, contentItem.standardNewExpireTime) && Intrinsics.areEqual(this.countdownHourNew, contentItem.countdownHourNew) && Intrinsics.areEqual(this.countdownHour, contentItem.countdownHour) && Intrinsics.areEqual(this.serverTime, contentItem.serverTime) && Intrinsics.areEqual(this.woolUser, contentItem.woolUser);
        }

        public int hashCode() {
            return ((((((((((((((((((((((((((((((((((((((this.title == null ? 0 : this.title.hashCode()) * 31) + (this.coverImage == null ? 0 : this.coverImage.hashCode())) * 31) + (this.redirectType == null ? 0 : this.redirectType.hashCode())) * 31) + (this.redirectId == null ? 0 : this.redirectId.hashCode())) * 31) + (this.movieType == null ? 0 : this.movieType.hashCode())) * 31) + (this.score == null ? 0 : this.score.hashCode())) * 31) + (this.publishTime == null ? 0 : this.publishTime.hashCode())) * 31) + (this.heat == null ? 0 : this.heat.hashCode())) * 31) + (this.order == null ? 0 : this.order.hashCode())) * 31) + (this.unlockPlayback == null ? 0 : this.unlockPlayback.hashCode())) * 31) + (this.languages == null ? 0 : this.languages.hashCode())) * 31) + (this.excludeChannelIds == null ? 0 : this.excludeChannelIds.hashCode())) * 31) + (this.memberLevel == null ? 0 : this.memberLevel.hashCode())) * 31) + (this.standardExpireTime == null ? 0 : this.standardExpireTime.hashCode())) * 31) + (this.indiaResolutionLabel == null ? 0 : this.indiaResolutionLabel.hashCode())) * 31) + (this.standardNewExpireTime == null ? 0 : this.standardNewExpireTime.hashCode())) * 31) + (this.countdownHourNew == null ? 0 : this.countdownHourNew.hashCode())) * 31) + (this.countdownHour == null ? 0 : this.countdownHour.hashCode())) * 31) + (this.serverTime == null ? 0 : this.serverTime.hashCode())) * 31) + (this.woolUser != null ? this.woolUser.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ContentItem(title=").append(this.title).append(", coverImage=").append(this.coverImage).append(", redirectType=").append(this.redirectType).append(", redirectId=").append(this.redirectId).append(", movieType=").append(this.movieType).append(", score=").append(this.score).append(", publishTime=").append(this.publishTime).append(", heat=").append(this.heat).append(", order=").append(this.order).append(", unlockPlayback=").append(this.unlockPlayback).append(", languages=").append(this.languages).append(", excludeChannelIds=");
            sb.append(this.excludeChannelIds).append(", memberLevel=").append(this.memberLevel).append(", standardExpireTime=").append(this.standardExpireTime).append(", indiaResolutionLabel=").append(this.indiaResolutionLabel).append(", standardNewExpireTime=").append(this.standardNewExpireTime).append(", countdownHourNew=").append(this.countdownHourNew).append(", countdownHour=").append(this.countdownHour).append(", serverTime=").append(this.serverTime).append(", woolUser=").append(this.woolUser).append(')');
            return sb.toString();
        }

        public ContentItem(@Nullable String title, @Nullable String coverImage, @Nullable Integer redirectType, @Nullable Long redirectId, @Nullable Integer movieType, @Nullable Double score, @Nullable Long publishTime, @Nullable Integer heat, @Nullable Integer order, @Nullable Boolean unlockPlayback, @Nullable List<String> list, @Nullable List<String> list2, @Nullable Integer memberLevel, @Nullable Long standardExpireTime, @Nullable String indiaResolutionLabel, @Nullable Long standardNewExpireTime, @Nullable Integer countdownHourNew, @Nullable Integer countdownHour, @Nullable Long serverTime, @Nullable Object woolUser) {
            this.title = title;
            this.coverImage = coverImage;
            this.redirectType = redirectType;
            this.redirectId = redirectId;
            this.movieType = movieType;
            this.score = score;
            this.publishTime = publishTime;
            this.heat = heat;
            this.order = order;
            this.unlockPlayback = unlockPlayback;
            this.languages = list;
            this.excludeChannelIds = list2;
            this.memberLevel = memberLevel;
            this.standardExpireTime = standardExpireTime;
            this.indiaResolutionLabel = indiaResolutionLabel;
            this.standardNewExpireTime = standardNewExpireTime;
            this.countdownHourNew = countdownHourNew;
            this.countdownHour = countdownHour;
            this.serverTime = serverTime;
            this.woolUser = woolUser;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ ContentItem(String str, String str2, Integer num, Long l, Integer num2, Double d, Long l2, Integer num3, Integer num4, Boolean bool, List list, List list2, Integer num5, Long l3, String str3, Long l4, Integer num6, Integer num7, Long l5, Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
            String str4 = (i & 1) != 0 ? null : str;
            String str5 = (i & 2) != 0 ? null : str2;
            Integer num8 = (i & 4) != 0 ? null : num;
            Long l6 = (i & 8) != 0 ? null : l;
            Integer num9 = (i & 16) != 0 ? null : num2;
            Double d2 = (i & 32) != 0 ? null : d;
            Long l7 = (i & 64) != 0 ? null : l2;
            Integer num10 = (i & 128) != 0 ? null : num3;
            Integer num11 = (i & 256) != 0 ? null : num4;
            Boolean bool2 = (i & 512) != 0 ? null : bool;
            List list3 = (i & 1024) != 0 ? null : list;
            List list4 = (i & 2048) != 0 ? null : list2;
            Integer num12 = (i & 4096) != 0 ? null : num5;
            Long l8 = (i & 8192) != 0 ? null : l3;
            String str6 = (i & 16384) != 0 ? null : str3;
            this(str4, str5, num8, l6, num9, d2, l7, num10, num11, bool2, list3, list4, num12, l8, str6, (i & 32768) != 0 ? null : l4, (i & 65536) != 0 ? null : num6, (i & 131072) != 0 ? null : num7, (i & 262144) != 0 ? null : l5, (i & 524288) != 0 ? null : obj);
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final String getCoverImage() {
            return this.coverImage;
        }

        @Nullable
        public final Integer getRedirectType() {
            return this.redirectType;
        }

        @Nullable
        public final Long getRedirectId() {
            return this.redirectId;
        }

        @Nullable
        public final Integer getMovieType() {
            return this.movieType;
        }

        @Nullable
        public final Double getScore() {
            return this.score;
        }

        @Nullable
        public final Long getPublishTime() {
            return this.publishTime;
        }

        @Nullable
        public final Integer getHeat() {
            return this.heat;
        }

        @Nullable
        public final Integer getOrder() {
            return this.order;
        }

        @Nullable
        public final Boolean getUnlockPlayback() {
            return this.unlockPlayback;
        }

        @Nullable
        public final List<String> getLanguages() {
            return this.languages;
        }

        @Nullable
        public final List<String> getExcludeChannelIds() {
            return this.excludeChannelIds;
        }

        @Nullable
        public final Integer getMemberLevel() {
            return this.memberLevel;
        }

        @Nullable
        public final Long getStandardExpireTime() {
            return this.standardExpireTime;
        }

        @Nullable
        public final String getIndiaResolutionLabel() {
            return this.indiaResolutionLabel;
        }

        @Nullable
        public final Long getStandardNewExpireTime() {
            return this.standardNewExpireTime;
        }

        @Nullable
        public final Integer getCountdownHourNew() {
            return this.countdownHourNew;
        }

        @Nullable
        public final Integer getCountdownHour() {
            return this.countdownHour;
        }

        @Nullable
        public final Long getServerTime() {
            return this.serverTime;
        }

        @Nullable
        public final Object getWoolUser() {
            return this.woolUser;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/cncverse/CastleTvProvider$MovieDetailsResponse;", "", "code", "", "msg", "", "data", "Lcom/cncverse/CastleTvProvider$MovieDetails;", "<init>", "(ILjava/lang/String;Lcom/cncverse/CastleTvProvider$MovieDetails;)V", "getCode", "()I", "getMsg", "()Ljava/lang/String;", "getData", "()Lcom/cncverse/CastleTvProvider$MovieDetails;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class MovieDetailsResponse {
        private final int code;

        @NotNull
        private final MovieDetails data;

        @NotNull
        private final String msg;

        public static /* synthetic */ MovieDetailsResponse copy$default(MovieDetailsResponse movieDetailsResponse, int i, String str, MovieDetails movieDetails, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = movieDetailsResponse.code;
            }
            if ((i2 & 2) != 0) {
                str = movieDetailsResponse.msg;
            }
            if ((i2 & 4) != 0) {
                movieDetails = movieDetailsResponse.data;
            }
            return movieDetailsResponse.copy(i, str, movieDetails);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCode() {
            return this.code;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMsg() {
            return this.msg;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final MovieDetails getData() {
            return this.data;
        }

        @NotNull
        public final MovieDetailsResponse copy(int code, @NotNull String msg, @NotNull MovieDetails data) {
            return new MovieDetailsResponse(code, msg, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MovieDetailsResponse)) {
                return false;
            }
            MovieDetailsResponse movieDetailsResponse = (MovieDetailsResponse) other;
            return this.code == movieDetailsResponse.code && Intrinsics.areEqual(this.msg, movieDetailsResponse.msg) && Intrinsics.areEqual(this.data, movieDetailsResponse.data);
        }

        public int hashCode() {
            return (((this.code * 31) + this.msg.hashCode()) * 31) + this.data.hashCode();
        }

        @NotNull
        public String toString() {
            return "MovieDetailsResponse(code=" + this.code + ", msg=" + this.msg + ", data=" + this.data + ')';
        }

        public MovieDetailsResponse(int code, @NotNull String msg, @NotNull MovieDetails data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        public final int getCode() {
            return this.code;
        }

        @NotNull
        public final String getMsg() {
            return this.msg;
        }

        @NotNull
        public final MovieDetails getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b_\b\u0086\b\u0018\u00002\u00020\u0001B\u0085\u0004\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u0011\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\t\u0012\u0010\b\u0002\u0010&\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u0011\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b1\u00102J\u0010\u0010b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u00104J\u000b\u0010c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010d\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u00109J\u0010\u0010e\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010<J\u000b\u0010f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010i\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010BJ\u000b\u0010j\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010k\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011HÆ\u0003J\u0010\u0010l\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010<J\u0010\u0010m\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u00104J\u0010\u0010n\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010<J\u0010\u0010o\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010BJ\u000b\u0010p\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010q\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u00104J\u0011\u0010r\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011HÆ\u0003J\u0011\u0010s\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011HÆ\u0003J\u0010\u0010t\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010BJ\u000b\u0010u\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010v\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0011HÆ\u0003J\u0011\u0010w\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0011HÆ\u0003J\u0011\u0010x\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u0011HÆ\u0003J\u0010\u0010y\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010<J\u0010\u0010z\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010<J\u0010\u0010{\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u00104J\u0010\u0010|\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010<J\u0010\u0010}\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010<J\u0011\u0010~\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010\u0011HÆ\u0003J\u0011\u0010\u007f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011HÆ\u0003J\u0012\u0010\u0080\u0001\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011HÆ\u0003J\u0012\u0010\u0081\u0001\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011HÆ\u0003J\u0011\u0010\u0082\u0001\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010<J\f\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0012\u0010\u0084\u0001\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u0011HÆ\u0003J\f\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\f\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u008e\u0004\u0010\u0087\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00112\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00112\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u00112\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u00112\u0010\b\u0002\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u00112\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\t2\u0010\b\u0002\u0010&\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010\u00112\u0010\b\u0002\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00112\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00112\u0010\b\u0002\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00112\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u00112\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u0001HÆ\u0001¢\u0006\u0003\u0010\u0088\u0001J\u0016\u0010\u0089\u0001\u001a\u00020\u000e2\t\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\u000b\u0010\u008b\u0001\u001a\u00020\tHÖ\u0081\u0004J\u000b\u0010\u008c\u0001\u001a\u00020\u0005HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u00105\u001a\u0004\b3\u00104R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010:\u001a\u0004\b8\u00109R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010=\u001a\u0004\b;\u0010<R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b>\u00107R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b?\u00107R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b@\u00107R\u0015\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\n\n\u0002\u0010C\u001a\u0004\bA\u0010BR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bD\u00107R\u0019\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\bE\u0010FR\u0015\u0010\u0012\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010=\u001a\u0004\bG\u0010<R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u00105\u001a\u0004\bH\u00104R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010=\u001a\u0004\bI\u0010<R\u0015\u0010\u0015\u001a\u0004\u0018\u00010\u000e¢\u0006\n\n\u0002\u0010C\u001a\u0004\bJ\u0010BR\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bK\u00107R\u0015\u0010\u0017\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u00105\u001a\u0004\bL\u00104R\u0019\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\bM\u0010FR\u0019\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\bN\u0010FR\u0015\u0010\u001a\u001a\u0004\u0018\u00010\u000e¢\u0006\n\n\u0002\u0010C\u001a\u0004\b\u001a\u0010BR\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bO\u00107R\u0019\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\bP\u0010FR\u0019\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010FR\u0019\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\bR\u0010FR\u0015\u0010!\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010=\u001a\u0004\bS\u0010<R\u0015\u0010\"\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010=\u001a\u0004\bT\u0010<R\u0015\u0010#\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u00105\u001a\u0004\bU\u00104R\u0015\u0010$\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010=\u001a\u0004\bV\u0010<R\u0015\u0010%\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010=\u001a\u0004\bW\u0010<R\u0019\u0010&\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\bX\u0010FR\u0019\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\bY\u0010FR\u0019\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010FR\u0019\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b[\u0010FR\u0015\u0010+\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010=\u001a\u0004\b\\\u0010<R\u0013\u0010,\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b]\u00107R\u0019\u0010-\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b^\u0010FR\u0013\u0010/\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b_\u0010`R\u0013\u00100\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\ba\u0010`¨\u0006\u008d\u0001"}, d2 = {"Lcom/cncverse/CastleTvProvider$MovieDetails;", "", "id", "", "title", "", "score", "", "movieType", "", "movieTypeName", "coverHorizontalImage", "coverVerticalImage", "unlockPlayback", "", "seasonDescription", "languages", "", "lastEpisodeCount", "serverTime", "totalNumber", "woolUser", "briefIntroduction", "publishTime", "tags", "countries", "isAuthorized", "originalTitle", "directors", "Lcom/cncverse/CastleTvProvider$Person;", "actors", "episodes", "Lcom/cncverse/CastleTvProvider$ApiEpisode;", "seasonNumber", "updateNumber", "watchCount", "commentTotal", "previewTime", "seasons", "Lcom/cncverse/CastleTvProvider$Season;", "audioTags", "countryIds", "tagIds", "resolution", "indiaResolutionLabel", "titbits", "Lcom/cncverse/CastleTvProvider$Titbit;", "honorTag", "downloadTag", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Long;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;Ljava/lang/Object;Ljava/lang/Object;)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTitle", "()Ljava/lang/String;", "getScore", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getMovieType", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMovieTypeName", "getCoverHorizontalImage", "getCoverVerticalImage", "getUnlockPlayback", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getSeasonDescription", "getLanguages", "()Ljava/util/List;", "getLastEpisodeCount", "getServerTime", "getTotalNumber", "getWoolUser", "getBriefIntroduction", "getPublishTime", "getTags", "getCountries", "getOriginalTitle", "getDirectors", "getActors", "getEpisodes", "getSeasonNumber", "getUpdateNumber", "getWatchCount", "getCommentTotal", "getPreviewTime", "getSeasons", "getAudioTags", "getCountryIds", "getTagIds", "getResolution", "getIndiaResolutionLabel", "getTitbits", "getHonorTag", "()Ljava/lang/Object;", "getDownloadTag", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Long;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;Ljava/lang/Object;Ljava/lang/Object;)Lcom/cncverse/CastleTvProvider$MovieDetails;", "equals", "other", "hashCode", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class MovieDetails {

        @Nullable
        private final List<Person> actors;

        @Nullable
        private final List<String> audioTags;

        @Nullable
        private final String briefIntroduction;

        @Nullable
        private final Integer commentTotal;

        @Nullable
        private final List<String> countries;

        @Nullable
        private final List<Long> countryIds;

        @Nullable
        private final String coverHorizontalImage;

        @Nullable
        private final String coverVerticalImage;

        @Nullable
        private final List<Person> directors;

        @Nullable
        private final Object downloadTag;

        @Nullable
        private final List<ApiEpisode> episodes;

        @Nullable
        private final Object honorTag;

        @Nullable
        private final Long id;

        @Nullable
        private final String indiaResolutionLabel;

        @Nullable
        private final Boolean isAuthorized;

        @Nullable
        private final List<String> languages;

        @Nullable
        private final Integer lastEpisodeCount;

        @Nullable
        private final Integer movieType;

        @Nullable
        private final String movieTypeName;

        @Nullable
        private final String originalTitle;

        @Nullable
        private final Integer previewTime;

        @Nullable
        private final Long publishTime;

        @Nullable
        private final Integer resolution;

        @Nullable
        private final Double score;

        @Nullable
        private final String seasonDescription;

        @Nullable
        private final Integer seasonNumber;

        @Nullable
        private final List<Season> seasons;

        @Nullable
        private final Long serverTime;

        @Nullable
        private final List<Long> tagIds;

        @Nullable
        private final List<String> tags;

        @Nullable
        private final List<Titbit> titbits;

        @Nullable
        private final String title;

        @Nullable
        private final Integer totalNumber;

        @Nullable
        private final Boolean unlockPlayback;

        @Nullable
        private final Integer updateNumber;

        @Nullable
        private final Long watchCount;

        @Nullable
        private final Boolean woolUser;

        public MovieDetails() {
            this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, -1, 31, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MovieDetails copy$default(MovieDetails movieDetails, Long l, String str, Double d, Integer num, String str2, String str3, String str4, Boolean bool, String str5, List list, Integer num2, Long l2, Integer num3, Boolean bool2, String str6, Long l3, List list2, List list3, Boolean bool3, String str7, List list4, List list5, List list6, Integer num4, Integer num5, Long l4, Integer num6, Integer num7, List list7, List list8, List list9, List list10, Integer num8, String str8, List list11, Object obj, Object obj2, int i, int i2, Object obj3) {
            Object obj4;
            Object obj5;
            List list12;
            List list13;
            List list14;
            Integer num9;
            Integer num10;
            Long l5;
            Integer num11;
            Integer num12;
            List list15;
            List list16;
            List list17;
            List list18;
            Integer num13;
            String str9;
            List list19;
            String str10;
            String str11;
            String str12;
            Boolean bool4;
            String str13;
            List list20;
            Integer num14;
            Long l6;
            Integer num15;
            Boolean bool5;
            Long l7;
            List list21;
            List list22;
            Boolean bool6;
            String str14;
            String str15;
            Double d2;
            Integer num16;
            String str16;
            Long l8 = (i & 1) != 0 ? movieDetails.id : l;
            String str17 = (i & 2) != 0 ? movieDetails.title : str;
            Double d3 = (i & 4) != 0 ? movieDetails.score : d;
            Integer num17 = (i & 8) != 0 ? movieDetails.movieType : num;
            String str18 = (i & 16) != 0 ? movieDetails.movieTypeName : str2;
            String str19 = (i & 32) != 0 ? movieDetails.coverHorizontalImage : str3;
            String str20 = (i & 64) != 0 ? movieDetails.coverVerticalImage : str4;
            Boolean bool7 = (i & 128) != 0 ? movieDetails.unlockPlayback : bool;
            String str21 = (i & 256) != 0 ? movieDetails.seasonDescription : str5;
            List list23 = (i & 512) != 0 ? movieDetails.languages : list;
            Integer num18 = (i & 1024) != 0 ? movieDetails.lastEpisodeCount : num2;
            Long l9 = (i & 2048) != 0 ? movieDetails.serverTime : l2;
            Integer num19 = (i & 4096) != 0 ? movieDetails.totalNumber : num3;
            Boolean bool8 = (i & 8192) != 0 ? movieDetails.woolUser : bool2;
            Long l10 = l8;
            String str22 = (i & 16384) != 0 ? movieDetails.briefIntroduction : str6;
            Long l11 = (i & 32768) != 0 ? movieDetails.publishTime : l3;
            List list24 = (i & 65536) != 0 ? movieDetails.tags : list2;
            List list25 = (i & 131072) != 0 ? movieDetails.countries : list3;
            Boolean bool9 = (i & 262144) != 0 ? movieDetails.isAuthorized : bool3;
            String str23 = (i & 524288) != 0 ? movieDetails.originalTitle : str7;
            List list26 = (i & 1048576) != 0 ? movieDetails.directors : list4;
            List list27 = (i & 2097152) != 0 ? movieDetails.actors : list5;
            List list28 = (i & 4194304) != 0 ? movieDetails.episodes : list6;
            Integer num20 = (i & 8388608) != 0 ? movieDetails.seasonNumber : num4;
            Integer num21 = (i & 16777216) != 0 ? movieDetails.updateNumber : num5;
            Long l12 = (i & 33554432) != 0 ? movieDetails.watchCount : l4;
            Integer num22 = (i & 67108864) != 0 ? movieDetails.commentTotal : num6;
            Integer num23 = (i & 134217728) != 0 ? movieDetails.previewTime : num7;
            List list29 = (i & 268435456) != 0 ? movieDetails.seasons : list7;
            List list30 = (i & 536870912) != 0 ? movieDetails.audioTags : list8;
            List list31 = (i & 1073741824) != 0 ? movieDetails.countryIds : list9;
            List list32 = (i & Integer.MIN_VALUE) != 0 ? movieDetails.tagIds : list10;
            Integer num24 = (i2 & 1) != 0 ? movieDetails.resolution : num8;
            String str24 = (i2 & 2) != 0 ? movieDetails.indiaResolutionLabel : str8;
            List list33 = (i2 & 4) != 0 ? movieDetails.titbits : list11;
            Object obj6 = (i2 & 8) != 0 ? movieDetails.honorTag : obj;
            if ((i2 & 16) != 0) {
                obj5 = obj6;
                obj4 = movieDetails.downloadTag;
                list13 = list27;
                list14 = list28;
                num9 = num20;
                num10 = num21;
                l5 = l12;
                num11 = num22;
                num12 = num23;
                list15 = list29;
                list16 = list30;
                list17 = list31;
                list18 = list32;
                num13 = num24;
                str9 = str24;
                list19 = list33;
                str10 = str22;
                str12 = str20;
                bool4 = bool7;
                str13 = str21;
                list20 = list23;
                num14 = num18;
                l6 = l9;
                num15 = num19;
                bool5 = bool8;
                l7 = l11;
                list21 = list24;
                list22 = list25;
                bool6 = bool9;
                str14 = str23;
                list12 = list26;
                str15 = str17;
                d2 = d3;
                num16 = num17;
                str16 = str18;
                str11 = str19;
            } else {
                obj4 = obj2;
                obj5 = obj6;
                list12 = list26;
                list13 = list27;
                list14 = list28;
                num9 = num20;
                num10 = num21;
                l5 = l12;
                num11 = num22;
                num12 = num23;
                list15 = list29;
                list16 = list30;
                list17 = list31;
                list18 = list32;
                num13 = num24;
                str9 = str24;
                list19 = list33;
                str10 = str22;
                str11 = str19;
                str12 = str20;
                bool4 = bool7;
                str13 = str21;
                list20 = list23;
                num14 = num18;
                l6 = l9;
                num15 = num19;
                bool5 = bool8;
                l7 = l11;
                list21 = list24;
                list22 = list25;
                bool6 = bool9;
                str14 = str23;
                str15 = str17;
                d2 = d3;
                num16 = num17;
                str16 = str18;
            }
            return movieDetails.copy(l10, str15, d2, num16, str16, str11, str12, bool4, str13, list20, num14, l6, num15, bool5, str10, l7, list21, list22, bool6, str14, list12, list13, list14, num9, num10, l5, num11, num12, list15, list16, list17, list18, num13, str9, list19, obj5, obj4);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Long getId() {
            return this.id;
        }

        @Nullable
        public final List<String> component10() {
            return this.languages;
        }

        @Nullable
        /* JADX INFO: renamed from: component11, reason: from getter */
        public final Integer getLastEpisodeCount() {
            return this.lastEpisodeCount;
        }

        @Nullable
        /* JADX INFO: renamed from: component12, reason: from getter */
        public final Long getServerTime() {
            return this.serverTime;
        }

        @Nullable
        /* JADX INFO: renamed from: component13, reason: from getter */
        public final Integer getTotalNumber() {
            return this.totalNumber;
        }

        @Nullable
        /* JADX INFO: renamed from: component14, reason: from getter */
        public final Boolean getWoolUser() {
            return this.woolUser;
        }

        @Nullable
        /* JADX INFO: renamed from: component15, reason: from getter */
        public final String getBriefIntroduction() {
            return this.briefIntroduction;
        }

        @Nullable
        /* JADX INFO: renamed from: component16, reason: from getter */
        public final Long getPublishTime() {
            return this.publishTime;
        }

        @Nullable
        public final List<String> component17() {
            return this.tags;
        }

        @Nullable
        public final List<String> component18() {
            return this.countries;
        }

        @Nullable
        /* JADX INFO: renamed from: component19, reason: from getter */
        public final Boolean getIsAuthorized() {
            return this.isAuthorized;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        /* JADX INFO: renamed from: component20, reason: from getter */
        public final String getOriginalTitle() {
            return this.originalTitle;
        }

        @Nullable
        public final List<Person> component21() {
            return this.directors;
        }

        @Nullable
        public final List<Person> component22() {
            return this.actors;
        }

        @Nullable
        public final List<ApiEpisode> component23() {
            return this.episodes;
        }

        @Nullable
        /* JADX INFO: renamed from: component24, reason: from getter */
        public final Integer getSeasonNumber() {
            return this.seasonNumber;
        }

        @Nullable
        /* JADX INFO: renamed from: component25, reason: from getter */
        public final Integer getUpdateNumber() {
            return this.updateNumber;
        }

        @Nullable
        /* JADX INFO: renamed from: component26, reason: from getter */
        public final Long getWatchCount() {
            return this.watchCount;
        }

        @Nullable
        /* JADX INFO: renamed from: component27, reason: from getter */
        public final Integer getCommentTotal() {
            return this.commentTotal;
        }

        @Nullable
        /* JADX INFO: renamed from: component28, reason: from getter */
        public final Integer getPreviewTime() {
            return this.previewTime;
        }

        @Nullable
        public final List<Season> component29() {
            return this.seasons;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Double getScore() {
            return this.score;
        }

        @Nullable
        public final List<String> component30() {
            return this.audioTags;
        }

        @Nullable
        public final List<Long> component31() {
            return this.countryIds;
        }

        @Nullable
        public final List<Long> component32() {
            return this.tagIds;
        }

        @Nullable
        /* JADX INFO: renamed from: component33, reason: from getter */
        public final Integer getResolution() {
            return this.resolution;
        }

        @Nullable
        /* JADX INFO: renamed from: component34, reason: from getter */
        public final String getIndiaResolutionLabel() {
            return this.indiaResolutionLabel;
        }

        @Nullable
        public final List<Titbit> component35() {
            return this.titbits;
        }

        @Nullable
        /* JADX INFO: renamed from: component36, reason: from getter */
        public final Object getHonorTag() {
            return this.honorTag;
        }

        @Nullable
        /* JADX INFO: renamed from: component37, reason: from getter */
        public final Object getDownloadTag() {
            return this.downloadTag;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getMovieType() {
            return this.movieType;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getMovieTypeName() {
            return this.movieTypeName;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getCoverHorizontalImage() {
            return this.coverHorizontalImage;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getCoverVerticalImage() {
            return this.coverVerticalImage;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Boolean getUnlockPlayback() {
            return this.unlockPlayback;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getSeasonDescription() {
            return this.seasonDescription;
        }

        @NotNull
        public final MovieDetails copy(@Nullable Long id, @Nullable String title, @Nullable Double score, @Nullable Integer movieType, @Nullable String movieTypeName, @Nullable String coverHorizontalImage, @Nullable String coverVerticalImage, @Nullable Boolean unlockPlayback, @Nullable String seasonDescription, @Nullable List<String> languages, @Nullable Integer lastEpisodeCount, @Nullable Long serverTime, @Nullable Integer totalNumber, @Nullable Boolean woolUser, @Nullable String briefIntroduction, @Nullable Long publishTime, @Nullable List<String> tags, @Nullable List<String> countries, @Nullable Boolean isAuthorized, @Nullable String originalTitle, @Nullable List<Person> directors, @Nullable List<Person> actors, @Nullable List<ApiEpisode> episodes, @Nullable Integer seasonNumber, @Nullable Integer updateNumber, @Nullable Long watchCount, @Nullable Integer commentTotal, @Nullable Integer previewTime, @Nullable List<Season> seasons, @Nullable List<String> audioTags, @Nullable List<Long> countryIds, @Nullable List<Long> tagIds, @Nullable Integer resolution, @Nullable String indiaResolutionLabel, @Nullable List<Titbit> titbits, @Nullable Object honorTag, @Nullable Object downloadTag) {
            return new MovieDetails(id, title, score, movieType, movieTypeName, coverHorizontalImage, coverVerticalImage, unlockPlayback, seasonDescription, languages, lastEpisodeCount, serverTime, totalNumber, woolUser, briefIntroduction, publishTime, tags, countries, isAuthorized, originalTitle, directors, actors, episodes, seasonNumber, updateNumber, watchCount, commentTotal, previewTime, seasons, audioTags, countryIds, tagIds, resolution, indiaResolutionLabel, titbits, honorTag, downloadTag);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MovieDetails)) {
                return false;
            }
            MovieDetails movieDetails = (MovieDetails) other;
            return Intrinsics.areEqual(this.id, movieDetails.id) && Intrinsics.areEqual(this.title, movieDetails.title) && Intrinsics.areEqual(this.score, movieDetails.score) && Intrinsics.areEqual(this.movieType, movieDetails.movieType) && Intrinsics.areEqual(this.movieTypeName, movieDetails.movieTypeName) && Intrinsics.areEqual(this.coverHorizontalImage, movieDetails.coverHorizontalImage) && Intrinsics.areEqual(this.coverVerticalImage, movieDetails.coverVerticalImage) && Intrinsics.areEqual(this.unlockPlayback, movieDetails.unlockPlayback) && Intrinsics.areEqual(this.seasonDescription, movieDetails.seasonDescription) && Intrinsics.areEqual(this.languages, movieDetails.languages) && Intrinsics.areEqual(this.lastEpisodeCount, movieDetails.lastEpisodeCount) && Intrinsics.areEqual(this.serverTime, movieDetails.serverTime) && Intrinsics.areEqual(this.totalNumber, movieDetails.totalNumber) && Intrinsics.areEqual(this.woolUser, movieDetails.woolUser) && Intrinsics.areEqual(this.briefIntroduction, movieDetails.briefIntroduction) && Intrinsics.areEqual(this.publishTime, movieDetails.publishTime) && Intrinsics.areEqual(this.tags, movieDetails.tags) && Intrinsics.areEqual(this.countries, movieDetails.countries) && Intrinsics.areEqual(this.isAuthorized, movieDetails.isAuthorized) && Intrinsics.areEqual(this.originalTitle, movieDetails.originalTitle) && Intrinsics.areEqual(this.directors, movieDetails.directors) && Intrinsics.areEqual(this.actors, movieDetails.actors) && Intrinsics.areEqual(this.episodes, movieDetails.episodes) && Intrinsics.areEqual(this.seasonNumber, movieDetails.seasonNumber) && Intrinsics.areEqual(this.updateNumber, movieDetails.updateNumber) && Intrinsics.areEqual(this.watchCount, movieDetails.watchCount) && Intrinsics.areEqual(this.commentTotal, movieDetails.commentTotal) && Intrinsics.areEqual(this.previewTime, movieDetails.previewTime) && Intrinsics.areEqual(this.seasons, movieDetails.seasons) && Intrinsics.areEqual(this.audioTags, movieDetails.audioTags) && Intrinsics.areEqual(this.countryIds, movieDetails.countryIds) && Intrinsics.areEqual(this.tagIds, movieDetails.tagIds) && Intrinsics.areEqual(this.resolution, movieDetails.resolution) && Intrinsics.areEqual(this.indiaResolutionLabel, movieDetails.indiaResolutionLabel) && Intrinsics.areEqual(this.titbits, movieDetails.titbits) && Intrinsics.areEqual(this.honorTag, movieDetails.honorTag) && Intrinsics.areEqual(this.downloadTag, movieDetails.downloadTag);
        }

        public int hashCode() {
            return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.score == null ? 0 : this.score.hashCode())) * 31) + (this.movieType == null ? 0 : this.movieType.hashCode())) * 31) + (this.movieTypeName == null ? 0 : this.movieTypeName.hashCode())) * 31) + (this.coverHorizontalImage == null ? 0 : this.coverHorizontalImage.hashCode())) * 31) + (this.coverVerticalImage == null ? 0 : this.coverVerticalImage.hashCode())) * 31) + (this.unlockPlayback == null ? 0 : this.unlockPlayback.hashCode())) * 31) + (this.seasonDescription == null ? 0 : this.seasonDescription.hashCode())) * 31) + (this.languages == null ? 0 : this.languages.hashCode())) * 31) + (this.lastEpisodeCount == null ? 0 : this.lastEpisodeCount.hashCode())) * 31) + (this.serverTime == null ? 0 : this.serverTime.hashCode())) * 31) + (this.totalNumber == null ? 0 : this.totalNumber.hashCode())) * 31) + (this.woolUser == null ? 0 : this.woolUser.hashCode())) * 31) + (this.briefIntroduction == null ? 0 : this.briefIntroduction.hashCode())) * 31) + (this.publishTime == null ? 0 : this.publishTime.hashCode())) * 31) + (this.tags == null ? 0 : this.tags.hashCode())) * 31) + (this.countries == null ? 0 : this.countries.hashCode())) * 31) + (this.isAuthorized == null ? 0 : this.isAuthorized.hashCode())) * 31) + (this.originalTitle == null ? 0 : this.originalTitle.hashCode())) * 31) + (this.directors == null ? 0 : this.directors.hashCode())) * 31) + (this.actors == null ? 0 : this.actors.hashCode())) * 31) + (this.episodes == null ? 0 : this.episodes.hashCode())) * 31) + (this.seasonNumber == null ? 0 : this.seasonNumber.hashCode())) * 31) + (this.updateNumber == null ? 0 : this.updateNumber.hashCode())) * 31) + (this.watchCount == null ? 0 : this.watchCount.hashCode())) * 31) + (this.commentTotal == null ? 0 : this.commentTotal.hashCode())) * 31) + (this.previewTime == null ? 0 : this.previewTime.hashCode())) * 31) + (this.seasons == null ? 0 : this.seasons.hashCode())) * 31) + (this.audioTags == null ? 0 : this.audioTags.hashCode())) * 31) + (this.countryIds == null ? 0 : this.countryIds.hashCode())) * 31) + (this.tagIds == null ? 0 : this.tagIds.hashCode())) * 31) + (this.resolution == null ? 0 : this.resolution.hashCode())) * 31) + (this.indiaResolutionLabel == null ? 0 : this.indiaResolutionLabel.hashCode())) * 31) + (this.titbits == null ? 0 : this.titbits.hashCode())) * 31) + (this.honorTag == null ? 0 : this.honorTag.hashCode())) * 31) + (this.downloadTag != null ? this.downloadTag.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("MovieDetails(id=").append(this.id).append(", title=").append(this.title).append(", score=").append(this.score).append(", movieType=").append(this.movieType).append(", movieTypeName=").append(this.movieTypeName).append(", coverHorizontalImage=").append(this.coverHorizontalImage).append(", coverVerticalImage=").append(this.coverVerticalImage).append(", unlockPlayback=").append(this.unlockPlayback).append(", seasonDescription=").append(this.seasonDescription).append(", languages=").append(this.languages).append(", lastEpisodeCount=").append(this.lastEpisodeCount).append(", serverTime=");
            sb.append(this.serverTime).append(", totalNumber=").append(this.totalNumber).append(", woolUser=").append(this.woolUser).append(", briefIntroduction=").append(this.briefIntroduction).append(", publishTime=").append(this.publishTime).append(", tags=").append(this.tags).append(", countries=").append(this.countries).append(", isAuthorized=").append(this.isAuthorized).append(", originalTitle=").append(this.originalTitle).append(", directors=").append(this.directors).append(", actors=").append(this.actors).append(", episodes=").append(this.episodes);
            sb.append(", seasonNumber=").append(this.seasonNumber).append(", updateNumber=").append(this.updateNumber).append(", watchCount=").append(this.watchCount).append(", commentTotal=").append(this.commentTotal).append(", previewTime=").append(this.previewTime).append(", seasons=").append(this.seasons).append(", audioTags=").append(this.audioTags).append(", countryIds=").append(this.countryIds).append(", tagIds=").append(this.tagIds).append(", resolution=").append(this.resolution).append(", indiaResolutionLabel=").append(this.indiaResolutionLabel).append(", titbits=");
            sb.append(this.titbits).append(", honorTag=").append(this.honorTag).append(", downloadTag=").append(this.downloadTag).append(')');
            return sb.toString();
        }

        public MovieDetails(@Nullable Long id, @Nullable String title, @Nullable Double score, @Nullable Integer movieType, @Nullable String movieTypeName, @Nullable String coverHorizontalImage, @Nullable String coverVerticalImage, @Nullable Boolean unlockPlayback, @Nullable String seasonDescription, @Nullable List<String> list, @Nullable Integer lastEpisodeCount, @Nullable Long serverTime, @Nullable Integer totalNumber, @Nullable Boolean woolUser, @Nullable String briefIntroduction, @Nullable Long publishTime, @Nullable List<String> list2, @Nullable List<String> list3, @Nullable Boolean isAuthorized, @Nullable String originalTitle, @Nullable List<Person> list4, @Nullable List<Person> list5, @Nullable List<ApiEpisode> list6, @Nullable Integer seasonNumber, @Nullable Integer updateNumber, @Nullable Long watchCount, @Nullable Integer commentTotal, @Nullable Integer previewTime, @Nullable List<Season> list7, @Nullable List<String> list8, @Nullable List<Long> list9, @Nullable List<Long> list10, @Nullable Integer resolution, @Nullable String indiaResolutionLabel, @Nullable List<Titbit> list11, @Nullable Object honorTag, @Nullable Object downloadTag) {
            this.id = id;
            this.title = title;
            this.score = score;
            this.movieType = movieType;
            this.movieTypeName = movieTypeName;
            this.coverHorizontalImage = coverHorizontalImage;
            this.coverVerticalImage = coverVerticalImage;
            this.unlockPlayback = unlockPlayback;
            this.seasonDescription = seasonDescription;
            this.languages = list;
            this.lastEpisodeCount = lastEpisodeCount;
            this.serverTime = serverTime;
            this.totalNumber = totalNumber;
            this.woolUser = woolUser;
            this.briefIntroduction = briefIntroduction;
            this.publishTime = publishTime;
            this.tags = list2;
            this.countries = list3;
            this.isAuthorized = isAuthorized;
            this.originalTitle = originalTitle;
            this.directors = list4;
            this.actors = list5;
            this.episodes = list6;
            this.seasonNumber = seasonNumber;
            this.updateNumber = updateNumber;
            this.watchCount = watchCount;
            this.commentTotal = commentTotal;
            this.previewTime = previewTime;
            this.seasons = list7;
            this.audioTags = list8;
            this.countryIds = list9;
            this.tagIds = list10;
            this.resolution = resolution;
            this.indiaResolutionLabel = indiaResolutionLabel;
            this.titbits = list11;
            this.honorTag = honorTag;
            this.downloadTag = downloadTag;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ MovieDetails(Long l, String str, Double d, Integer num, String str2, String str3, String str4, Boolean bool, String str5, List list, Integer num2, Long l2, Integer num3, Boolean bool2, String str6, Long l3, List list2, List list3, Boolean bool3, String str7, List list4, List list5, List list6, Integer num4, Integer num5, Long l4, Integer num6, Integer num7, List list7, List list8, List list9, List list10, Integer num8, String str8, List list11, Object obj, Object obj2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            Long l5 = (i & 1) != 0 ? null : l;
            String str9 = (i & 2) != 0 ? null : str;
            Double d2 = (i & 4) != 0 ? null : d;
            Integer num9 = (i & 8) != 0 ? null : num;
            String str10 = (i & 16) != 0 ? null : str2;
            String str11 = (i & 32) != 0 ? null : str3;
            String str12 = (i & 64) != 0 ? null : str4;
            Boolean bool4 = (i & 128) != 0 ? null : bool;
            String str13 = (i & 256) != 0 ? null : str5;
            List list12 = (i & 512) != 0 ? null : list;
            Integer num10 = (i & 1024) != 0 ? null : num2;
            Long l6 = (i & 2048) != 0 ? null : l2;
            Integer num11 = (i & 4096) != 0 ? null : num3;
            Boolean bool5 = (i & 8192) != 0 ? null : bool2;
            String str14 = (i & 16384) != 0 ? null : str6;
            this(l5, str9, d2, num9, str10, str11, str12, bool4, str13, list12, num10, l6, num11, bool5, str14, (i & 32768) != 0 ? null : l3, (i & 65536) != 0 ? null : list2, (i & 131072) != 0 ? null : list3, (i & 262144) != 0 ? null : bool3, (i & 524288) != 0 ? null : str7, (i & 1048576) != 0 ? null : list4, (i & 2097152) != 0 ? null : list5, (i & 4194304) != 0 ? null : list6, (i & 8388608) != 0 ? null : num4, (i & 16777216) != 0 ? null : num5, (i & 33554432) != 0 ? null : l4, (i & 67108864) != 0 ? null : num6, (i & 134217728) != 0 ? null : num7, (i & 268435456) != 0 ? null : list7, (i & 536870912) != 0 ? null : list8, (i & 1073741824) != 0 ? null : list9, (i & Integer.MIN_VALUE) != 0 ? null : list10, (i2 & 1) != 0 ? null : num8, (i2 & 2) != 0 ? null : str8, (i2 & 4) != 0 ? null : list11, (i2 & 8) != 0 ? null : obj, (i2 & 16) != 0 ? null : obj2);
        }

        @Nullable
        public final Long getId() {
            return this.id;
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final Double getScore() {
            return this.score;
        }

        @Nullable
        public final Integer getMovieType() {
            return this.movieType;
        }

        @Nullable
        public final String getMovieTypeName() {
            return this.movieTypeName;
        }

        @Nullable
        public final String getCoverHorizontalImage() {
            return this.coverHorizontalImage;
        }

        @Nullable
        public final String getCoverVerticalImage() {
            return this.coverVerticalImage;
        }

        @Nullable
        public final Boolean getUnlockPlayback() {
            return this.unlockPlayback;
        }

        @Nullable
        public final String getSeasonDescription() {
            return this.seasonDescription;
        }

        @Nullable
        public final List<String> getLanguages() {
            return this.languages;
        }

        @Nullable
        public final Integer getLastEpisodeCount() {
            return this.lastEpisodeCount;
        }

        @Nullable
        public final Long getServerTime() {
            return this.serverTime;
        }

        @Nullable
        public final Integer getTotalNumber() {
            return this.totalNumber;
        }

        @Nullable
        public final Boolean getWoolUser() {
            return this.woolUser;
        }

        @Nullable
        public final String getBriefIntroduction() {
            return this.briefIntroduction;
        }

        @Nullable
        public final Long getPublishTime() {
            return this.publishTime;
        }

        @Nullable
        public final List<String> getTags() {
            return this.tags;
        }

        @Nullable
        public final List<String> getCountries() {
            return this.countries;
        }

        @Nullable
        public final Boolean isAuthorized() {
            return this.isAuthorized;
        }

        @Nullable
        public final String getOriginalTitle() {
            return this.originalTitle;
        }

        @Nullable
        public final List<Person> getDirectors() {
            return this.directors;
        }

        @Nullable
        public final List<Person> getActors() {
            return this.actors;
        }

        @Nullable
        public final List<ApiEpisode> getEpisodes() {
            return this.episodes;
        }

        @Nullable
        public final Integer getSeasonNumber() {
            return this.seasonNumber;
        }

        @Nullable
        public final Integer getUpdateNumber() {
            return this.updateNumber;
        }

        @Nullable
        public final Long getWatchCount() {
            return this.watchCount;
        }

        @Nullable
        public final Integer getCommentTotal() {
            return this.commentTotal;
        }

        @Nullable
        public final Integer getPreviewTime() {
            return this.previewTime;
        }

        @Nullable
        public final List<Season> getSeasons() {
            return this.seasons;
        }

        @Nullable
        public final List<String> getAudioTags() {
            return this.audioTags;
        }

        @Nullable
        public final List<Long> getCountryIds() {
            return this.countryIds;
        }

        @Nullable
        public final List<Long> getTagIds() {
            return this.tagIds;
        }

        @Nullable
        public final Integer getResolution() {
            return this.resolution;
        }

        @Nullable
        public final String getIndiaResolutionLabel() {
            return this.indiaResolutionLabel;
        }

        @Nullable
        public final List<Titbit> getTitbits() {
            return this.titbits;
        }

        @Nullable
        public final Object getHonorTag() {
            return this.honorTag;
        }

        @Nullable
        public final Object getDownloadTag() {
            return this.downloadTag;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J2\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0005HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/cncverse/CastleTvProvider$Person;", "", "id", "", "name", "", "avatar", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getName", "()Ljava/lang/String;", "getAvatar", "component1", "component2", "component3", "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)Lcom/cncverse/CastleTvProvider$Person;", "equals", "", "other", "hashCode", "", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Person {

        @Nullable
        private final String avatar;

        @Nullable
        private final Long id;

        @Nullable
        private final String name;

        public Person() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ Person copy$default(Person person, Long l, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                l = person.id;
            }
            if ((i & 2) != 0) {
                str = person.name;
            }
            if ((i & 4) != 0) {
                str2 = person.avatar;
            }
            return person.copy(l, str, str2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Long getId() {
            return this.id;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getAvatar() {
            return this.avatar;
        }

        @NotNull
        public final Person copy(@Nullable Long id, @Nullable String name, @Nullable String avatar) {
            return new Person(id, name, avatar);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Person)) {
                return false;
            }
            Person person = (Person) other;
            return Intrinsics.areEqual(this.id, person.id) && Intrinsics.areEqual(this.name, person.name) && Intrinsics.areEqual(this.avatar, person.avatar);
        }

        public int hashCode() {
            return ((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.avatar != null ? this.avatar.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "Person(id=" + this.id + ", name=" + this.name + ", avatar=" + this.avatar + ')';
        }

        public Person(@Nullable Long id, @Nullable String name, @Nullable String avatar) {
            this.id = id;
            this.name = name;
            this.avatar = avatar;
        }

        public /* synthetic */ Person(Long l, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : l, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2);
        }

        @Nullable
        public final Long getId() {
            return this.id;
        }

        @Nullable
        public final String getName() {
            return this.name;
        }

        @Nullable
        public final String getAvatar() {
            return this.avatar;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u008b\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010%\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010&\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000b\u0010'\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0011\u0010)\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÆ\u0003J\u0010\u0010*\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u0010+\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0011\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000bHÆ\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0092\u0001\u0010.\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010/J\u0014\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00103\u001a\u00020\u0007HÖ\u0081\u0004J\n\u00104\u001a\u00020\u0005HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001d\u0010\u001aR\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0015\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b \u0010\u001aR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b!\u0010\u001aR\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b#\u0010\u0015¨\u00065"}, d2 = {"Lcom/cncverse/CastleTvProvider$ApiEpisode;", "", "id", "", "title", "", "number", "", "coverImage", "duration", "videos", "", "Lcom/cncverse/CastleTvProvider$VideoQuality;", "playResolution", "mobileTrafficPlayResolution", "tracks", "Lcom/cncverse/CastleTvProvider$Track;", "onlineTime", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Long;)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTitle", "()Ljava/lang/String;", "getNumber", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCoverImage", "getDuration", "getVideos", "()Ljava/util/List;", "getPlayResolution", "getMobileTrafficPlayResolution", "getTracks", "getOnlineTime", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Long;)Lcom/cncverse/CastleTvProvider$ApiEpisode;", "equals", "", "other", "hashCode", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ApiEpisode {

        @Nullable
        private final String coverImage;

        @Nullable
        private final Integer duration;

        @Nullable
        private final Long id;

        @Nullable
        private final Integer mobileTrafficPlayResolution;

        @Nullable
        private final Integer number;

        @Nullable
        private final Long onlineTime;

        @Nullable
        private final Integer playResolution;

        @Nullable
        private final String title;

        @Nullable
        private final List<Track> tracks;

        @Nullable
        private final List<VideoQuality> videos;

        public ApiEpisode() {
            this(null, null, null, null, null, null, null, null, null, null, 1023, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiEpisode copy$default(ApiEpisode apiEpisode, Long l, String str, Integer num, String str2, Integer num2, List list, Integer num3, Integer num4, List list2, Long l2, int i, Object obj) {
            if ((i & 1) != 0) {
                l = apiEpisode.id;
            }
            if ((i & 2) != 0) {
                str = apiEpisode.title;
            }
            if ((i & 4) != 0) {
                num = apiEpisode.number;
            }
            if ((i & 8) != 0) {
                str2 = apiEpisode.coverImage;
            }
            if ((i & 16) != 0) {
                num2 = apiEpisode.duration;
            }
            if ((i & 32) != 0) {
                list = apiEpisode.videos;
            }
            if ((i & 64) != 0) {
                num3 = apiEpisode.playResolution;
            }
            if ((i & 128) != 0) {
                num4 = apiEpisode.mobileTrafficPlayResolution;
            }
            if ((i & 256) != 0) {
                list2 = apiEpisode.tracks;
            }
            if ((i & 512) != 0) {
                l2 = apiEpisode.onlineTime;
            }
            List list3 = list2;
            Long l3 = l2;
            Integer num5 = num3;
            Integer num6 = num4;
            Integer num7 = num2;
            List list4 = list;
            return apiEpisode.copy(l, str, num, str2, num7, list4, num5, num6, list3, l3);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Long getId() {
            return this.id;
        }

        @Nullable
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Long getOnlineTime() {
            return this.onlineTime;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getNumber() {
            return this.number;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getCoverImage() {
            return this.coverImage;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getDuration() {
            return this.duration;
        }

        @Nullable
        public final List<VideoQuality> component6() {
            return this.videos;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getPlayResolution() {
            return this.playResolution;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getMobileTrafficPlayResolution() {
            return this.mobileTrafficPlayResolution;
        }

        @Nullable
        public final List<Track> component9() {
            return this.tracks;
        }

        @NotNull
        public final ApiEpisode copy(@Nullable Long id, @Nullable String title, @Nullable Integer number, @Nullable String coverImage, @Nullable Integer duration, @Nullable List<VideoQuality> videos, @Nullable Integer playResolution, @Nullable Integer mobileTrafficPlayResolution, @Nullable List<Track> tracks, @Nullable Long onlineTime) {
            return new ApiEpisode(id, title, number, coverImage, duration, videos, playResolution, mobileTrafficPlayResolution, tracks, onlineTime);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ApiEpisode)) {
                return false;
            }
            ApiEpisode apiEpisode = (ApiEpisode) other;
            return Intrinsics.areEqual(this.id, apiEpisode.id) && Intrinsics.areEqual(this.title, apiEpisode.title) && Intrinsics.areEqual(this.number, apiEpisode.number) && Intrinsics.areEqual(this.coverImage, apiEpisode.coverImage) && Intrinsics.areEqual(this.duration, apiEpisode.duration) && Intrinsics.areEqual(this.videos, apiEpisode.videos) && Intrinsics.areEqual(this.playResolution, apiEpisode.playResolution) && Intrinsics.areEqual(this.mobileTrafficPlayResolution, apiEpisode.mobileTrafficPlayResolution) && Intrinsics.areEqual(this.tracks, apiEpisode.tracks) && Intrinsics.areEqual(this.onlineTime, apiEpisode.onlineTime);
        }

        public int hashCode() {
            return ((((((((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.number == null ? 0 : this.number.hashCode())) * 31) + (this.coverImage == null ? 0 : this.coverImage.hashCode())) * 31) + (this.duration == null ? 0 : this.duration.hashCode())) * 31) + (this.videos == null ? 0 : this.videos.hashCode())) * 31) + (this.playResolution == null ? 0 : this.playResolution.hashCode())) * 31) + (this.mobileTrafficPlayResolution == null ? 0 : this.mobileTrafficPlayResolution.hashCode())) * 31) + (this.tracks == null ? 0 : this.tracks.hashCode())) * 31) + (this.onlineTime != null ? this.onlineTime.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "ApiEpisode(id=" + this.id + ", title=" + this.title + ", number=" + this.number + ", coverImage=" + this.coverImage + ", duration=" + this.duration + ", videos=" + this.videos + ", playResolution=" + this.playResolution + ", mobileTrafficPlayResolution=" + this.mobileTrafficPlayResolution + ", tracks=" + this.tracks + ", onlineTime=" + this.onlineTime + ')';
        }

        public ApiEpisode(@Nullable Long id, @Nullable String title, @Nullable Integer number, @Nullable String coverImage, @Nullable Integer duration, @Nullable List<VideoQuality> list, @Nullable Integer playResolution, @Nullable Integer mobileTrafficPlayResolution, @Nullable List<Track> list2, @Nullable Long onlineTime) {
            this.id = id;
            this.title = title;
            this.number = number;
            this.coverImage = coverImage;
            this.duration = duration;
            this.videos = list;
            this.playResolution = playResolution;
            this.mobileTrafficPlayResolution = mobileTrafficPlayResolution;
            this.tracks = list2;
            this.onlineTime = onlineTime;
        }

        public /* synthetic */ ApiEpisode(Long l, String str, Integer num, String str2, Integer num2, List list, Integer num3, Integer num4, List list2, Long l2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : l, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : num2, (i & 32) != 0 ? null : list, (i & 64) != 0 ? null : num3, (i & 128) != 0 ? null : num4, (i & 256) != 0 ? null : list2, (i & 512) != 0 ? null : l2);
        }

        @Nullable
        public final Long getId() {
            return this.id;
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final Integer getNumber() {
            return this.number;
        }

        @Nullable
        public final String getCoverImage() {
            return this.coverImage;
        }

        @Nullable
        public final Integer getDuration() {
            return this.duration;
        }

        @Nullable
        public final List<VideoQuality> getVideos() {
            return this.videos;
        }

        @Nullable
        public final Integer getPlayResolution() {
            return this.playResolution;
        }

        @Nullable
        public final Integer getMobileTrafficPlayResolution() {
            return this.mobileTrafficPlayResolution;
        }

        @Nullable
        public final List<Track> getTracks() {
            return this.tracks;
        }

        @Nullable
        public final Long getOnlineTime() {
            return this.onlineTime;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0015J>\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0014\u0010\u001d\u001a\u00020\t2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010 \u001a\u00020\u0005HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lcom/cncverse/CastleTvProvider$VideoQuality;", "", "resolution", "", "resolutionDescription", "", "size", "", "premiumProPermission", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Boolean;)V", "getResolution", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getResolutionDescription", "()Ljava/lang/String;", "getSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getPremiumProPermission", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Boolean;)Lcom/cncverse/CastleTvProvider$VideoQuality;", "equals", "other", "hashCode", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VideoQuality {

        @Nullable
        private final Boolean premiumProPermission;

        @Nullable
        private final Integer resolution;

        @Nullable
        private final String resolutionDescription;

        @Nullable
        private final Long size;

        public VideoQuality() {
            this(null, null, null, null, 15, null);
        }

        public static /* synthetic */ VideoQuality copy$default(VideoQuality videoQuality, Integer num, String str, Long l, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                num = videoQuality.resolution;
            }
            if ((i & 2) != 0) {
                str = videoQuality.resolutionDescription;
            }
            if ((i & 4) != 0) {
                l = videoQuality.size;
            }
            if ((i & 8) != 0) {
                bool = videoQuality.premiumProPermission;
            }
            return videoQuality.copy(num, str, l, bool);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getResolution() {
            return this.resolution;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getResolutionDescription() {
            return this.resolutionDescription;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Long getSize() {
            return this.size;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Boolean getPremiumProPermission() {
            return this.premiumProPermission;
        }

        @NotNull
        public final VideoQuality copy(@Nullable Integer resolution, @Nullable String resolutionDescription, @Nullable Long size, @Nullable Boolean premiumProPermission) {
            return new VideoQuality(resolution, resolutionDescription, size, premiumProPermission);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoQuality)) {
                return false;
            }
            VideoQuality videoQuality = (VideoQuality) other;
            return Intrinsics.areEqual(this.resolution, videoQuality.resolution) && Intrinsics.areEqual(this.resolutionDescription, videoQuality.resolutionDescription) && Intrinsics.areEqual(this.size, videoQuality.size) && Intrinsics.areEqual(this.premiumProPermission, videoQuality.premiumProPermission);
        }

        public int hashCode() {
            return ((((((this.resolution == null ? 0 : this.resolution.hashCode()) * 31) + (this.resolutionDescription == null ? 0 : this.resolutionDescription.hashCode())) * 31) + (this.size == null ? 0 : this.size.hashCode())) * 31) + (this.premiumProPermission != null ? this.premiumProPermission.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "VideoQuality(resolution=" + this.resolution + ", resolutionDescription=" + this.resolutionDescription + ", size=" + this.size + ", premiumProPermission=" + this.premiumProPermission + ')';
        }

        public VideoQuality(@Nullable Integer resolution, @Nullable String resolutionDescription, @Nullable Long size, @Nullable Boolean premiumProPermission) {
            this.resolution = resolution;
            this.resolutionDescription = resolutionDescription;
            this.size = size;
            this.premiumProPermission = premiumProPermission;
        }

        public /* synthetic */ VideoQuality(Integer num, String str, Long l, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : l, (i & 8) != 0 ? null : bool);
        }

        @Nullable
        public final Integer getResolution() {
            return this.resolution;
        }

        @Nullable
        public final String getResolutionDescription() {
            return this.resolutionDescription;
        }

        @Nullable
        public final Long getSize() {
            return this.size;
        }

        @Nullable
        public final Boolean getPremiumProPermission() {
            return this.premiumProPermission;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b \b\u0086\b\u0018\u00002\u00020\u0001Bm\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010!\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0016J\u0011\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000bHÆ\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011Jt\u0010%\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010&J\u0014\u0010'\u001a\u00020\b2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010)\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010*\u001a\u00020\u0005HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0007\u0010\u0016R\u0015\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0018\u0010\u0016R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0015\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001b\u0010\u0011R\u0015\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001c\u0010\u0011¨\u0006+"}, d2 = {"Lcom/cncverse/CastleTvProvider$Track;", "", "languageId", "", "languageName", "", "abbreviate", "isDefault", "", "existIndividualVideo", "subtitles", "", "order", "index", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getLanguageId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLanguageName", "()Ljava/lang/String;", "getAbbreviate", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getExistIndividualVideo", "getSubtitles", "()Ljava/util/List;", "getOrder", "getIndex", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/cncverse/CastleTvProvider$Track;", "equals", "other", "hashCode", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Track {

        @Nullable
        private final String abbreviate;

        @Nullable
        private final Boolean existIndividualVideo;

        @Nullable
        private final Integer index;

        @Nullable
        private final Boolean isDefault;

        @Nullable
        private final Integer languageId;

        @Nullable
        private final String languageName;

        @Nullable
        private final Integer order;

        @Nullable
        private final List<Object> subtitles;

        public Track() {
            this(null, null, null, null, null, null, null, null, 255, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Track copy$default(Track track, Integer num, String str, String str2, Boolean bool, Boolean bool2, List list, Integer num2, Integer num3, int i, Object obj) {
            if ((i & 1) != 0) {
                num = track.languageId;
            }
            if ((i & 2) != 0) {
                str = track.languageName;
            }
            if ((i & 4) != 0) {
                str2 = track.abbreviate;
            }
            if ((i & 8) != 0) {
                bool = track.isDefault;
            }
            if ((i & 16) != 0) {
                bool2 = track.existIndividualVideo;
            }
            if ((i & 32) != 0) {
                list = track.subtitles;
            }
            if ((i & 64) != 0) {
                num2 = track.order;
            }
            if ((i & 128) != 0) {
                num3 = track.index;
            }
            Integer num4 = num2;
            Integer num5 = num3;
            Boolean bool3 = bool2;
            List list2 = list;
            return track.copy(num, str, str2, bool, bool3, list2, num4, num5);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getLanguageId() {
            return this.languageId;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLanguageName() {
            return this.languageName;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getAbbreviate() {
            return this.abbreviate;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Boolean getIsDefault() {
            return this.isDefault;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Boolean getExistIndividualVideo() {
            return this.existIndividualVideo;
        }

        @Nullable
        public final List<Object> component6() {
            return this.subtitles;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getOrder() {
            return this.order;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getIndex() {
            return this.index;
        }

        @NotNull
        public final Track copy(@Nullable Integer languageId, @Nullable String languageName, @Nullable String abbreviate, @Nullable Boolean isDefault, @Nullable Boolean existIndividualVideo, @Nullable List<? extends Object> subtitles, @Nullable Integer order, @Nullable Integer index) {
            return new Track(languageId, languageName, abbreviate, isDefault, existIndividualVideo, subtitles, order, index);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Track)) {
                return false;
            }
            Track track = (Track) other;
            return Intrinsics.areEqual(this.languageId, track.languageId) && Intrinsics.areEqual(this.languageName, track.languageName) && Intrinsics.areEqual(this.abbreviate, track.abbreviate) && Intrinsics.areEqual(this.isDefault, track.isDefault) && Intrinsics.areEqual(this.existIndividualVideo, track.existIndividualVideo) && Intrinsics.areEqual(this.subtitles, track.subtitles) && Intrinsics.areEqual(this.order, track.order) && Intrinsics.areEqual(this.index, track.index);
        }

        public int hashCode() {
            return ((((((((((((((this.languageId == null ? 0 : this.languageId.hashCode()) * 31) + (this.languageName == null ? 0 : this.languageName.hashCode())) * 31) + (this.abbreviate == null ? 0 : this.abbreviate.hashCode())) * 31) + (this.isDefault == null ? 0 : this.isDefault.hashCode())) * 31) + (this.existIndividualVideo == null ? 0 : this.existIndividualVideo.hashCode())) * 31) + (this.subtitles == null ? 0 : this.subtitles.hashCode())) * 31) + (this.order == null ? 0 : this.order.hashCode())) * 31) + (this.index != null ? this.index.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "Track(languageId=" + this.languageId + ", languageName=" + this.languageName + ", abbreviate=" + this.abbreviate + ", isDefault=" + this.isDefault + ", existIndividualVideo=" + this.existIndividualVideo + ", subtitles=" + this.subtitles + ", order=" + this.order + ", index=" + this.index + ')';
        }

        public Track(@Nullable Integer languageId, @Nullable String languageName, @Nullable String abbreviate, @Nullable Boolean isDefault, @Nullable Boolean existIndividualVideo, @Nullable List<? extends Object> list, @Nullable Integer order, @Nullable Integer index) {
            this.languageId = languageId;
            this.languageName = languageName;
            this.abbreviate = abbreviate;
            this.isDefault = isDefault;
            this.existIndividualVideo = existIndividualVideo;
            this.subtitles = list;
            this.order = order;
            this.index = index;
        }

        public /* synthetic */ Track(Integer num, String str, String str2, Boolean bool, Boolean bool2, List list, Integer num2, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : bool, (i & 16) != 0 ? null : bool2, (i & 32) != 0 ? null : list, (i & 64) != 0 ? null : num2, (i & 128) != 0 ? null : num3);
        }

        @Nullable
        public final Integer getLanguageId() {
            return this.languageId;
        }

        @Nullable
        public final String getLanguageName() {
            return this.languageName;
        }

        @Nullable
        public final String getAbbreviate() {
            return this.abbreviate;
        }

        @Nullable
        public final Boolean isDefault() {
            return this.isDefault;
        }

        @Nullable
        public final Boolean getExistIndividualVideo() {
            return this.existIndividualVideo;
        }

        @Nullable
        public final List<Object> getSubtitles() {
            return this.subtitles;
        }

        @Nullable
        public final Integer getOrder() {
            return this.order;
        }

        @Nullable
        public final Integer getIndex() {
            return this.index;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0014J>\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0014\u0010\u001c\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0007HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\b\u0010\u0014¨\u0006 "}, d2 = {"Lcom/cncverse/CastleTvProvider$Season;", "", "movieId", "", "number", "", "description", "", "isCurrent", "", "<init>", "(Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;)V", "getMovieId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getNumber", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getDescription", "()Ljava/lang/String;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/cncverse/CastleTvProvider$Season;", "equals", "other", "hashCode", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Season {

        @Nullable
        private final String description;

        @Nullable
        private final Boolean isCurrent;

        @Nullable
        private final Long movieId;

        @Nullable
        private final Integer number;

        public Season() {
            this(null, null, null, null, 15, null);
        }

        public static /* synthetic */ Season copy$default(Season season, Long l, Integer num, String str, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                l = season.movieId;
            }
            if ((i & 2) != 0) {
                num = season.number;
            }
            if ((i & 4) != 0) {
                str = season.description;
            }
            if ((i & 8) != 0) {
                bool = season.isCurrent;
            }
            return season.copy(l, num, str, bool);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Long getMovieId() {
            return this.movieId;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getNumber() {
            return this.number;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Boolean getIsCurrent() {
            return this.isCurrent;
        }

        @NotNull
        public final Season copy(@Nullable Long movieId, @Nullable Integer number, @Nullable String description, @Nullable Boolean isCurrent) {
            return new Season(movieId, number, description, isCurrent);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Season)) {
                return false;
            }
            Season season = (Season) other;
            return Intrinsics.areEqual(this.movieId, season.movieId) && Intrinsics.areEqual(this.number, season.number) && Intrinsics.areEqual(this.description, season.description) && Intrinsics.areEqual(this.isCurrent, season.isCurrent);
        }

        public int hashCode() {
            return ((((((this.movieId == null ? 0 : this.movieId.hashCode()) * 31) + (this.number == null ? 0 : this.number.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.isCurrent != null ? this.isCurrent.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "Season(movieId=" + this.movieId + ", number=" + this.number + ", description=" + this.description + ", isCurrent=" + this.isCurrent + ')';
        }

        public Season(@Nullable Long movieId, @Nullable Integer number, @Nullable String description, @Nullable Boolean isCurrent) {
            this.movieId = movieId;
            this.number = number;
            this.description = description;
            this.isCurrent = isCurrent;
        }

        public /* synthetic */ Season(Long l, Integer num, String str, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : l, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : bool);
        }

        @Nullable
        public final Long getMovieId() {
            return this.movieId;
        }

        @Nullable
        public final Integer getNumber() {
            return this.number;
        }

        @Nullable
        public final String getDescription() {
            return this.description;
        }

        @Nullable
        public final Boolean isCurrent() {
            return this.isCurrent;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J>\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/cncverse/CastleTvProvider$Titbit;", "", "id", "", "name", "videoCategory", "", "coverImage", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "getVideoCategory", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCoverImage", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/cncverse/CastleTvProvider$Titbit;", "equals", "", "other", "hashCode", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Titbit {

        @Nullable
        private final String coverImage;

        @Nullable
        private final String id;

        @Nullable
        private final String name;

        @Nullable
        private final Integer videoCategory;

        public Titbit() {
            this(null, null, null, null, 15, null);
        }

        public static /* synthetic */ Titbit copy$default(Titbit titbit, String str, String str2, Integer num, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = titbit.id;
            }
            if ((i & 2) != 0) {
                str2 = titbit.name;
            }
            if ((i & 4) != 0) {
                num = titbit.videoCategory;
            }
            if ((i & 8) != 0) {
                str3 = titbit.coverImage;
            }
            return titbit.copy(str, str2, num, str3);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getVideoCategory() {
            return this.videoCategory;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getCoverImage() {
            return this.coverImage;
        }

        @NotNull
        public final Titbit copy(@Nullable String id, @Nullable String name, @Nullable Integer videoCategory, @Nullable String coverImage) {
            return new Titbit(id, name, videoCategory, coverImage);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Titbit)) {
                return false;
            }
            Titbit titbit = (Titbit) other;
            return Intrinsics.areEqual(this.id, titbit.id) && Intrinsics.areEqual(this.name, titbit.name) && Intrinsics.areEqual(this.videoCategory, titbit.videoCategory) && Intrinsics.areEqual(this.coverImage, titbit.coverImage);
        }

        public int hashCode() {
            return ((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.videoCategory == null ? 0 : this.videoCategory.hashCode())) * 31) + (this.coverImage != null ? this.coverImage.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "Titbit(id=" + this.id + ", name=" + this.name + ", videoCategory=" + this.videoCategory + ", coverImage=" + this.coverImage + ')';
        }

        public Titbit(@Nullable String id, @Nullable String name, @Nullable Integer videoCategory, @Nullable String coverImage) {
            this.id = id;
            this.name = name;
            this.videoCategory = videoCategory;
            this.coverImage = coverImage;
        }

        public /* synthetic */ Titbit(String str, String str2, Integer num, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : str3);
        }

        @Nullable
        public final String getId() {
            return this.id;
        }

        @Nullable
        public final String getName() {
            return this.name;
        }

        @Nullable
        public final Integer getVideoCategory() {
            return this.videoCategory;
        }

        @Nullable
        public final String getCoverImage() {
            return this.coverImage;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/cncverse/CastleTvProvider$SearchApiResponse;", "", "code", "", "msg", "", "data", "Lcom/cncverse/CastleTvProvider$SearchData;", "<init>", "(ILjava/lang/String;Lcom/cncverse/CastleTvProvider$SearchData;)V", "getCode", "()I", "getMsg", "()Ljava/lang/String;", "getData", "()Lcom/cncverse/CastleTvProvider$SearchData;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SearchApiResponse {
        private final int code;

        @NotNull
        private final SearchData data;

        @NotNull
        private final String msg;

        public static /* synthetic */ SearchApiResponse copy$default(SearchApiResponse searchApiResponse, int i, String str, SearchData searchData, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = searchApiResponse.code;
            }
            if ((i2 & 2) != 0) {
                str = searchApiResponse.msg;
            }
            if ((i2 & 4) != 0) {
                searchData = searchApiResponse.data;
            }
            return searchApiResponse.copy(i, str, searchData);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCode() {
            return this.code;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMsg() {
            return this.msg;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final SearchData getData() {
            return this.data;
        }

        @NotNull
        public final SearchApiResponse copy(int code, @NotNull String msg, @NotNull SearchData data) {
            return new SearchApiResponse(code, msg, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SearchApiResponse)) {
                return false;
            }
            SearchApiResponse searchApiResponse = (SearchApiResponse) other;
            return this.code == searchApiResponse.code && Intrinsics.areEqual(this.msg, searchApiResponse.msg) && Intrinsics.areEqual(this.data, searchApiResponse.data);
        }

        public int hashCode() {
            return (((this.code * 31) + this.msg.hashCode()) * 31) + this.data.hashCode();
        }

        @NotNull
        public String toString() {
            return "SearchApiResponse(code=" + this.code + ", msg=" + this.msg + ", data=" + this.data + ')';
        }

        public SearchApiResponse(int code, @NotNull String msg, @NotNull SearchData data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        public final int getCode() {
            return this.code;
        }

        @NotNull
        public final String getMsg() {
            return this.msg;
        }

        @NotNull
        public final SearchData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u0011\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003JP\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\rR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0010\u0010\rR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0011\u0010\rR\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006!"}, d2 = {"Lcom/cncverse/CastleTvProvider$SearchData;", "", "page", "", "pages", "size", "total", "rows", "", "Lcom/cncverse/CastleTvProvider$SearchResultItem;", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;)V", "getPage", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPages", "getSize", "getTotal", "getRows", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;)Lcom/cncverse/CastleTvProvider$SearchData;", "equals", "", "other", "hashCode", "toString", "", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SearchData {

        @Nullable
        private final Integer page;

        @Nullable
        private final Integer pages;

        @Nullable
        private final List<SearchResultItem> rows;

        @Nullable
        private final Integer size;

        @Nullable
        private final Integer total;

        public SearchData() {
            this(null, null, null, null, null, 31, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SearchData copy$default(SearchData searchData, Integer num, Integer num2, Integer num3, Integer num4, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                num = searchData.page;
            }
            if ((i & 2) != 0) {
                num2 = searchData.pages;
            }
            if ((i & 4) != 0) {
                num3 = searchData.size;
            }
            if ((i & 8) != 0) {
                num4 = searchData.total;
            }
            if ((i & 16) != 0) {
                list = searchData.rows;
            }
            List list2 = list;
            Integer num5 = num3;
            return searchData.copy(num, num2, num5, num4, list2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getPage() {
            return this.page;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getPages() {
            return this.pages;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getSize() {
            return this.size;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getTotal() {
            return this.total;
        }

        @Nullable
        public final List<SearchResultItem> component5() {
            return this.rows;
        }

        @NotNull
        public final SearchData copy(@Nullable Integer page, @Nullable Integer pages, @Nullable Integer size, @Nullable Integer total, @Nullable List<SearchResultItem> rows) {
            return new SearchData(page, pages, size, total, rows);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SearchData)) {
                return false;
            }
            SearchData searchData = (SearchData) other;
            return Intrinsics.areEqual(this.page, searchData.page) && Intrinsics.areEqual(this.pages, searchData.pages) && Intrinsics.areEqual(this.size, searchData.size) && Intrinsics.areEqual(this.total, searchData.total) && Intrinsics.areEqual(this.rows, searchData.rows);
        }

        public int hashCode() {
            return ((((((((this.page == null ? 0 : this.page.hashCode()) * 31) + (this.pages == null ? 0 : this.pages.hashCode())) * 31) + (this.size == null ? 0 : this.size.hashCode())) * 31) + (this.total == null ? 0 : this.total.hashCode())) * 31) + (this.rows != null ? this.rows.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "SearchData(page=" + this.page + ", pages=" + this.pages + ", size=" + this.size + ", total=" + this.total + ", rows=" + this.rows + ')';
        }

        public SearchData(@Nullable Integer page, @Nullable Integer pages, @Nullable Integer size, @Nullable Integer total, @Nullable List<SearchResultItem> list) {
            this.page = page;
            this.pages = pages;
            this.size = size;
            this.total = total;
            this.rows = list;
        }

        public /* synthetic */ SearchData(Integer num, Integer num2, Integer num3, Integer num4, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : num3, (i & 8) != 0 ? null : num4, (i & 16) != 0 ? null : list);
        }

        @Nullable
        public final Integer getPage() {
            return this.page;
        }

        @Nullable
        public final Integer getPages() {
            return this.pages;
        }

        @Nullable
        public final Integer getSize() {
            return this.size;
        }

        @Nullable
        public final Integer getTotal() {
            return this.total;
        }

        @Nullable
        public final List<SearchResultItem> getRows() {
            return this.rows;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b<\b\u0086\b\u0018\u00002\u00020\u0001Bå\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u000b\u00107\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u00108\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010!J\u0010\u00109\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010$J\u000b\u0010:\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010=\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010*J\u000b\u0010>\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010?\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011HÆ\u0003J\u0010\u0010@\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010$J\u0010\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u0010B\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010*J\u000b\u0010C\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0011\u0010E\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011HÆ\u0003J\u0011\u0010F\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011HÆ\u0003Jì\u0001\u0010G\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00112\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011HÆ\u0001¢\u0006\u0002\u0010HJ\u0014\u0010I\u001a\u00020\u000e2\b\u0010J\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010K\u001a\u00020\tHÖ\u0081\u0004J\n\u0010L\u001a\u00020\u0005HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010%\u001a\u0004\b#\u0010$R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001fR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u0015\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\n\n\u0002\u0010+\u001a\u0004\b)\u0010*R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001fR\u0019\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0015\u0010\u0012\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010%\u001a\u0004\b/\u0010$R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b0\u0010\u001cR\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u000e¢\u0006\n\n\u0002\u0010+\u001a\u0004\b1\u0010*R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001fR\u0015\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b3\u0010\u001cR\u0019\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b4\u0010.R\u0019\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b5\u0010.¨\u0006M"}, d2 = {"Lcom/cncverse/CastleTvProvider$SearchResultItem;", "", "id", "", "title", "", "score", "", "movieType", "", "movieTypeName", "coverHorizontalImage", "coverVerticalImage", "unlockPlayback", "", "seasonDescription", "languages", "", "lastEpisodeCount", "serverTime", "woolUser", "briefIntroduction", "publishTime", "tags", "countries", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Long;Ljava/util/List;Ljava/util/List;)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTitle", "()Ljava/lang/String;", "getScore", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getMovieType", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMovieTypeName", "getCoverHorizontalImage", "getCoverVerticalImage", "getUnlockPlayback", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getSeasonDescription", "getLanguages", "()Ljava/util/List;", "getLastEpisodeCount", "getServerTime", "getWoolUser", "getBriefIntroduction", "getPublishTime", "getTags", "getCountries", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Long;Ljava/util/List;Ljava/util/List;)Lcom/cncverse/CastleTvProvider$SearchResultItem;", "equals", "other", "hashCode", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SearchResultItem {

        @Nullable
        private final String briefIntroduction;

        @Nullable
        private final List<String> countries;

        @Nullable
        private final String coverHorizontalImage;

        @Nullable
        private final String coverVerticalImage;

        @Nullable
        private final Long id;

        @Nullable
        private final List<String> languages;

        @Nullable
        private final Integer lastEpisodeCount;

        @Nullable
        private final Integer movieType;

        @Nullable
        private final String movieTypeName;

        @Nullable
        private final Long publishTime;

        @Nullable
        private final Double score;

        @Nullable
        private final String seasonDescription;

        @Nullable
        private final Long serverTime;

        @Nullable
        private final List<String> tags;

        @Nullable
        private final String title;

        @Nullable
        private final Boolean unlockPlayback;

        @Nullable
        private final Boolean woolUser;

        public SearchResultItem() {
            this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 131071, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SearchResultItem copy$default(SearchResultItem searchResultItem, Long l, String str, Double d, Integer num, String str2, String str3, String str4, Boolean bool, String str5, List list, Integer num2, Long l2, Boolean bool2, String str6, Long l3, List list2, List list3, int i, Object obj) {
            List list4;
            List list5;
            Long l4;
            SearchResultItem searchResultItem2;
            Long l5;
            String str7;
            Double d2;
            Integer num3;
            String str8;
            String str9;
            String str10;
            Boolean bool3;
            String str11;
            List list6;
            Integer num4;
            Long l6;
            Boolean bool4;
            String str12;
            Long l7 = (i & 1) != 0 ? searchResultItem.id : l;
            String str13 = (i & 2) != 0 ? searchResultItem.title : str;
            Double d3 = (i & 4) != 0 ? searchResultItem.score : d;
            Integer num5 = (i & 8) != 0 ? searchResultItem.movieType : num;
            String str14 = (i & 16) != 0 ? searchResultItem.movieTypeName : str2;
            String str15 = (i & 32) != 0 ? searchResultItem.coverHorizontalImage : str3;
            String str16 = (i & 64) != 0 ? searchResultItem.coverVerticalImage : str4;
            Boolean bool5 = (i & 128) != 0 ? searchResultItem.unlockPlayback : bool;
            String str17 = (i & 256) != 0 ? searchResultItem.seasonDescription : str5;
            List list7 = (i & 512) != 0 ? searchResultItem.languages : list;
            Integer num6 = (i & 1024) != 0 ? searchResultItem.lastEpisodeCount : num2;
            Long l8 = (i & 2048) != 0 ? searchResultItem.serverTime : l2;
            Boolean bool6 = (i & 4096) != 0 ? searchResultItem.woolUser : bool2;
            String str18 = (i & 8192) != 0 ? searchResultItem.briefIntroduction : str6;
            Long l9 = l7;
            Long l10 = (i & 16384) != 0 ? searchResultItem.publishTime : l3;
            List list8 = (i & 32768) != 0 ? searchResultItem.tags : list2;
            if ((i & 65536) != 0) {
                list5 = list8;
                list4 = searchResultItem.countries;
                l5 = l10;
                str7 = str13;
                d2 = d3;
                num3 = num5;
                str8 = str14;
                str9 = str15;
                str10 = str16;
                bool3 = bool5;
                str11 = str17;
                list6 = list7;
                num4 = num6;
                l6 = l8;
                bool4 = bool6;
                str12 = str18;
                l4 = l9;
                searchResultItem2 = searchResultItem;
            } else {
                list4 = list3;
                list5 = list8;
                l4 = l9;
                searchResultItem2 = searchResultItem;
                l5 = l10;
                str7 = str13;
                d2 = d3;
                num3 = num5;
                str8 = str14;
                str9 = str15;
                str10 = str16;
                bool3 = bool5;
                str11 = str17;
                list6 = list7;
                num4 = num6;
                l6 = l8;
                bool4 = bool6;
                str12 = str18;
            }
            return searchResultItem2.copy(l4, str7, d2, num3, str8, str9, str10, bool3, str11, list6, num4, l6, bool4, str12, l5, list5, list4);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Long getId() {
            return this.id;
        }

        @Nullable
        public final List<String> component10() {
            return this.languages;
        }

        @Nullable
        /* JADX INFO: renamed from: component11, reason: from getter */
        public final Integer getLastEpisodeCount() {
            return this.lastEpisodeCount;
        }

        @Nullable
        /* JADX INFO: renamed from: component12, reason: from getter */
        public final Long getServerTime() {
            return this.serverTime;
        }

        @Nullable
        /* JADX INFO: renamed from: component13, reason: from getter */
        public final Boolean getWoolUser() {
            return this.woolUser;
        }

        @Nullable
        /* JADX INFO: renamed from: component14, reason: from getter */
        public final String getBriefIntroduction() {
            return this.briefIntroduction;
        }

        @Nullable
        /* JADX INFO: renamed from: component15, reason: from getter */
        public final Long getPublishTime() {
            return this.publishTime;
        }

        @Nullable
        public final List<String> component16() {
            return this.tags;
        }

        @Nullable
        public final List<String> component17() {
            return this.countries;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Double getScore() {
            return this.score;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getMovieType() {
            return this.movieType;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getMovieTypeName() {
            return this.movieTypeName;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getCoverHorizontalImage() {
            return this.coverHorizontalImage;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getCoverVerticalImage() {
            return this.coverVerticalImage;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Boolean getUnlockPlayback() {
            return this.unlockPlayback;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getSeasonDescription() {
            return this.seasonDescription;
        }

        @NotNull
        public final SearchResultItem copy(@Nullable Long id, @Nullable String title, @Nullable Double score, @Nullable Integer movieType, @Nullable String movieTypeName, @Nullable String coverHorizontalImage, @Nullable String coverVerticalImage, @Nullable Boolean unlockPlayback, @Nullable String seasonDescription, @Nullable List<String> languages, @Nullable Integer lastEpisodeCount, @Nullable Long serverTime, @Nullable Boolean woolUser, @Nullable String briefIntroduction, @Nullable Long publishTime, @Nullable List<String> tags, @Nullable List<String> countries) {
            return new SearchResultItem(id, title, score, movieType, movieTypeName, coverHorizontalImage, coverVerticalImage, unlockPlayback, seasonDescription, languages, lastEpisodeCount, serverTime, woolUser, briefIntroduction, publishTime, tags, countries);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SearchResultItem)) {
                return false;
            }
            SearchResultItem searchResultItem = (SearchResultItem) other;
            return Intrinsics.areEqual(this.id, searchResultItem.id) && Intrinsics.areEqual(this.title, searchResultItem.title) && Intrinsics.areEqual(this.score, searchResultItem.score) && Intrinsics.areEqual(this.movieType, searchResultItem.movieType) && Intrinsics.areEqual(this.movieTypeName, searchResultItem.movieTypeName) && Intrinsics.areEqual(this.coverHorizontalImage, searchResultItem.coverHorizontalImage) && Intrinsics.areEqual(this.coverVerticalImage, searchResultItem.coverVerticalImage) && Intrinsics.areEqual(this.unlockPlayback, searchResultItem.unlockPlayback) && Intrinsics.areEqual(this.seasonDescription, searchResultItem.seasonDescription) && Intrinsics.areEqual(this.languages, searchResultItem.languages) && Intrinsics.areEqual(this.lastEpisodeCount, searchResultItem.lastEpisodeCount) && Intrinsics.areEqual(this.serverTime, searchResultItem.serverTime) && Intrinsics.areEqual(this.woolUser, searchResultItem.woolUser) && Intrinsics.areEqual(this.briefIntroduction, searchResultItem.briefIntroduction) && Intrinsics.areEqual(this.publishTime, searchResultItem.publishTime) && Intrinsics.areEqual(this.tags, searchResultItem.tags) && Intrinsics.areEqual(this.countries, searchResultItem.countries);
        }

        public int hashCode() {
            return ((((((((((((((((((((((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.score == null ? 0 : this.score.hashCode())) * 31) + (this.movieType == null ? 0 : this.movieType.hashCode())) * 31) + (this.movieTypeName == null ? 0 : this.movieTypeName.hashCode())) * 31) + (this.coverHorizontalImage == null ? 0 : this.coverHorizontalImage.hashCode())) * 31) + (this.coverVerticalImage == null ? 0 : this.coverVerticalImage.hashCode())) * 31) + (this.unlockPlayback == null ? 0 : this.unlockPlayback.hashCode())) * 31) + (this.seasonDescription == null ? 0 : this.seasonDescription.hashCode())) * 31) + (this.languages == null ? 0 : this.languages.hashCode())) * 31) + (this.lastEpisodeCount == null ? 0 : this.lastEpisodeCount.hashCode())) * 31) + (this.serverTime == null ? 0 : this.serverTime.hashCode())) * 31) + (this.woolUser == null ? 0 : this.woolUser.hashCode())) * 31) + (this.briefIntroduction == null ? 0 : this.briefIntroduction.hashCode())) * 31) + (this.publishTime == null ? 0 : this.publishTime.hashCode())) * 31) + (this.tags == null ? 0 : this.tags.hashCode())) * 31) + (this.countries != null ? this.countries.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("SearchResultItem(id=").append(this.id).append(", title=").append(this.title).append(", score=").append(this.score).append(", movieType=").append(this.movieType).append(", movieTypeName=").append(this.movieTypeName).append(", coverHorizontalImage=").append(this.coverHorizontalImage).append(", coverVerticalImage=").append(this.coverVerticalImage).append(", unlockPlayback=").append(this.unlockPlayback).append(", seasonDescription=").append(this.seasonDescription).append(", languages=").append(this.languages).append(", lastEpisodeCount=").append(this.lastEpisodeCount).append(", serverTime=");
            sb.append(this.serverTime).append(", woolUser=").append(this.woolUser).append(", briefIntroduction=").append(this.briefIntroduction).append(", publishTime=").append(this.publishTime).append(", tags=").append(this.tags).append(", countries=").append(this.countries).append(')');
            return sb.toString();
        }

        public SearchResultItem(@Nullable Long id, @Nullable String title, @Nullable Double score, @Nullable Integer movieType, @Nullable String movieTypeName, @Nullable String coverHorizontalImage, @Nullable String coverVerticalImage, @Nullable Boolean unlockPlayback, @Nullable String seasonDescription, @Nullable List<String> list, @Nullable Integer lastEpisodeCount, @Nullable Long serverTime, @Nullable Boolean woolUser, @Nullable String briefIntroduction, @Nullable Long publishTime, @Nullable List<String> list2, @Nullable List<String> list3) {
            this.id = id;
            this.title = title;
            this.score = score;
            this.movieType = movieType;
            this.movieTypeName = movieTypeName;
            this.coverHorizontalImage = coverHorizontalImage;
            this.coverVerticalImage = coverVerticalImage;
            this.unlockPlayback = unlockPlayback;
            this.seasonDescription = seasonDescription;
            this.languages = list;
            this.lastEpisodeCount = lastEpisodeCount;
            this.serverTime = serverTime;
            this.woolUser = woolUser;
            this.briefIntroduction = briefIntroduction;
            this.publishTime = publishTime;
            this.tags = list2;
            this.countries = list3;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ SearchResultItem(Long l, String str, Double d, Integer num, String str2, String str3, String str4, Boolean bool, String str5, List list, Integer num2, Long l2, Boolean bool2, String str6, Long l3, List list2, List list3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            Long l4 = (i & 1) != 0 ? null : l;
            String str7 = (i & 2) != 0 ? null : str;
            Double d2 = (i & 4) != 0 ? null : d;
            Integer num3 = (i & 8) != 0 ? null : num;
            String str8 = (i & 16) != 0 ? null : str2;
            String str9 = (i & 32) != 0 ? null : str3;
            String str10 = (i & 64) != 0 ? null : str4;
            Boolean bool3 = (i & 128) != 0 ? null : bool;
            String str11 = (i & 256) != 0 ? null : str5;
            List list4 = (i & 512) != 0 ? null : list;
            Integer num4 = (i & 1024) != 0 ? null : num2;
            Long l5 = (i & 2048) != 0 ? null : l2;
            Boolean bool4 = (i & 4096) != 0 ? null : bool2;
            String str12 = (i & 8192) != 0 ? null : str6;
            Long l6 = (i & 16384) != 0 ? null : l3;
            this(l4, str7, d2, num3, str8, str9, str10, bool3, str11, list4, num4, l5, bool4, str12, l6, (i & 32768) != 0 ? null : list2, (i & 65536) != 0 ? null : list3);
        }

        @Nullable
        public final Long getId() {
            return this.id;
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final Double getScore() {
            return this.score;
        }

        @Nullable
        public final Integer getMovieType() {
            return this.movieType;
        }

        @Nullable
        public final String getMovieTypeName() {
            return this.movieTypeName;
        }

        @Nullable
        public final String getCoverHorizontalImage() {
            return this.coverHorizontalImage;
        }

        @Nullable
        public final String getCoverVerticalImage() {
            return this.coverVerticalImage;
        }

        @Nullable
        public final Boolean getUnlockPlayback() {
            return this.unlockPlayback;
        }

        @Nullable
        public final String getSeasonDescription() {
            return this.seasonDescription;
        }

        @Nullable
        public final List<String> getLanguages() {
            return this.languages;
        }

        @Nullable
        public final Integer getLastEpisodeCount() {
            return this.lastEpisodeCount;
        }

        @Nullable
        public final Long getServerTime() {
            return this.serverTime;
        }

        @Nullable
        public final Boolean getWoolUser() {
            return this.woolUser;
        }

        @Nullable
        public final String getBriefIntroduction() {
            return this.briefIntroduction;
        }

        @Nullable
        public final Long getPublishTime() {
            return this.publishTime;
        }

        @Nullable
        public final List<String> getTags() {
            return this.tags;
        }

        @Nullable
        public final List<String> getCountries() {
            return this.countries;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/cncverse/CastleTvProvider$VideoResponse;", "", "code", "", "msg", "", "data", "Lcom/cncverse/CastleTvProvider$VideoData;", "<init>", "(ILjava/lang/String;Lcom/cncverse/CastleTvProvider$VideoData;)V", "getCode", "()I", "getMsg", "()Ljava/lang/String;", "getData", "()Lcom/cncverse/CastleTvProvider$VideoData;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VideoResponse {
        private final int code;

        @NotNull
        private final VideoData data;

        @NotNull
        private final String msg;

        public static /* synthetic */ VideoResponse copy$default(VideoResponse videoResponse, int i, String str, VideoData videoData, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = videoResponse.code;
            }
            if ((i2 & 2) != 0) {
                str = videoResponse.msg;
            }
            if ((i2 & 4) != 0) {
                videoData = videoResponse.data;
            }
            return videoResponse.copy(i, str, videoData);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCode() {
            return this.code;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMsg() {
            return this.msg;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final VideoData getData() {
            return this.data;
        }

        @NotNull
        public final VideoResponse copy(int code, @NotNull String msg, @NotNull VideoData data) {
            return new VideoResponse(code, msg, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoResponse)) {
                return false;
            }
            VideoResponse videoResponse = (VideoResponse) other;
            return this.code == videoResponse.code && Intrinsics.areEqual(this.msg, videoResponse.msg) && Intrinsics.areEqual(this.data, videoResponse.data);
        }

        public int hashCode() {
            return (((this.code * 31) + this.msg.hashCode()) * 31) + this.data.hashCode();
        }

        @NotNull
        public String toString() {
            return "VideoResponse(code=" + this.code + ", msg=" + this.msg + ", data=" + this.data + ')';
        }

        public VideoResponse(int code, @NotNull String msg, @NotNull VideoData data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        public final int getCode() {
            return this.code;
        }

        @NotNull
        public final String getMsg() {
            return this.msg;
        }

        @NotNull
        public final VideoData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bg\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\t\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0011\u0010 \u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003J\u0011\u0010!\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\tHÆ\u0003J\u0010\u0010\"\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010#\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0016Jn\u0010$\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010%J\u0014\u0010&\u001a\u00020\u00072\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010(\u001a\u00020)HÖ\u0081\u0004J\n\u0010*\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0006\u0010\u0016R\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0015\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001b\u0010\u0016R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001c\u0010\u0016¨\u0006+"}, d2 = {"Lcom/cncverse/CastleTvProvider$VideoData;", "", "videoUrl", "", "expireTime", "", "isPreview", "", "videos", "", "Lcom/cncverse/CastleTvProvider$VideoQuality;", "subtitles", "Lcom/cncverse/CastleTvProvider$SubtitleData;", "inBlacklist", "permissionDenied", "<init>", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getVideoUrl", "()Ljava/lang/String;", "getExpireTime", "()Ljava/lang/Long;", "Ljava/lang/Long;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getVideos", "()Ljava/util/List;", "getSubtitles", "getInBlacklist", "getPermissionDenied", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lcom/cncverse/CastleTvProvider$VideoData;", "equals", "other", "hashCode", "", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VideoData {

        @Nullable
        private final Long expireTime;

        @Nullable
        private final Boolean inBlacklist;

        @Nullable
        private final Boolean isPreview;

        @Nullable
        private final Boolean permissionDenied;

        @Nullable
        private final List<SubtitleData> subtitles;

        @Nullable
        private final String videoUrl;

        @Nullable
        private final List<VideoQuality> videos;

        public VideoData() {
            this(null, null, null, null, null, null, null, 127, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ VideoData copy$default(VideoData videoData, String str, Long l, Boolean bool, List list, List list2, Boolean bool2, Boolean bool3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = videoData.videoUrl;
            }
            if ((i & 2) != 0) {
                l = videoData.expireTime;
            }
            if ((i & 4) != 0) {
                bool = videoData.isPreview;
            }
            if ((i & 8) != 0) {
                list = videoData.videos;
            }
            if ((i & 16) != 0) {
                list2 = videoData.subtitles;
            }
            if ((i & 32) != 0) {
                bool2 = videoData.inBlacklist;
            }
            if ((i & 64) != 0) {
                bool3 = videoData.permissionDenied;
            }
            Boolean bool4 = bool2;
            Boolean bool5 = bool3;
            List list3 = list2;
            Boolean bool6 = bool;
            return videoData.copy(str, l, bool6, list, list3, bool4, bool5);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getVideoUrl() {
            return this.videoUrl;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Long getExpireTime() {
            return this.expireTime;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Boolean getIsPreview() {
            return this.isPreview;
        }

        @Nullable
        public final List<VideoQuality> component4() {
            return this.videos;
        }

        @Nullable
        public final List<SubtitleData> component5() {
            return this.subtitles;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Boolean getInBlacklist() {
            return this.inBlacklist;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Boolean getPermissionDenied() {
            return this.permissionDenied;
        }

        @NotNull
        public final VideoData copy(@Nullable String videoUrl, @Nullable Long expireTime, @Nullable Boolean isPreview, @Nullable List<VideoQuality> videos, @Nullable List<SubtitleData> subtitles, @Nullable Boolean inBlacklist, @Nullable Boolean permissionDenied) {
            return new VideoData(videoUrl, expireTime, isPreview, videos, subtitles, inBlacklist, permissionDenied);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoData)) {
                return false;
            }
            VideoData videoData = (VideoData) other;
            return Intrinsics.areEqual(this.videoUrl, videoData.videoUrl) && Intrinsics.areEqual(this.expireTime, videoData.expireTime) && Intrinsics.areEqual(this.isPreview, videoData.isPreview) && Intrinsics.areEqual(this.videos, videoData.videos) && Intrinsics.areEqual(this.subtitles, videoData.subtitles) && Intrinsics.areEqual(this.inBlacklist, videoData.inBlacklist) && Intrinsics.areEqual(this.permissionDenied, videoData.permissionDenied);
        }

        public int hashCode() {
            return ((((((((((((this.videoUrl == null ? 0 : this.videoUrl.hashCode()) * 31) + (this.expireTime == null ? 0 : this.expireTime.hashCode())) * 31) + (this.isPreview == null ? 0 : this.isPreview.hashCode())) * 31) + (this.videos == null ? 0 : this.videos.hashCode())) * 31) + (this.subtitles == null ? 0 : this.subtitles.hashCode())) * 31) + (this.inBlacklist == null ? 0 : this.inBlacklist.hashCode())) * 31) + (this.permissionDenied != null ? this.permissionDenied.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "VideoData(videoUrl=" + this.videoUrl + ", expireTime=" + this.expireTime + ", isPreview=" + this.isPreview + ", videos=" + this.videos + ", subtitles=" + this.subtitles + ", inBlacklist=" + this.inBlacklist + ", permissionDenied=" + this.permissionDenied + ')';
        }

        public VideoData(@Nullable String videoUrl, @Nullable Long expireTime, @Nullable Boolean isPreview, @Nullable List<VideoQuality> list, @Nullable List<SubtitleData> list2, @Nullable Boolean inBlacklist, @Nullable Boolean permissionDenied) {
            this.videoUrl = videoUrl;
            this.expireTime = expireTime;
            this.isPreview = isPreview;
            this.videos = list;
            this.subtitles = list2;
            this.inBlacklist = inBlacklist;
            this.permissionDenied = permissionDenied;
        }

        public /* synthetic */ VideoData(String str, Long l, Boolean bool, List list, List list2, Boolean bool2, Boolean bool3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : l, (i & 4) != 0 ? null : bool, (i & 8) != 0 ? null : list, (i & 16) != 0 ? null : list2, (i & 32) != 0 ? null : bool2, (i & 64) != 0 ? null : bool3);
        }

        @Nullable
        public final String getVideoUrl() {
            return this.videoUrl;
        }

        @Nullable
        public final Long getExpireTime() {
            return this.expireTime;
        }

        @Nullable
        public final Boolean isPreview() {
            return this.isPreview;
        }

        @Nullable
        public final List<VideoQuality> getVideos() {
            return this.videos;
        }

        @Nullable
        public final List<SubtitleData> getSubtitles() {
            return this.subtitles;
        }

        @Nullable
        public final Boolean getInBlacklist() {
            return this.inBlacklist;
        }

        @Nullable
        public final Boolean getPermissionDenied() {
            return this.permissionDenied;
        }
    }

    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0019\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJV\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0014\u0010\u001e\u001a\u00020\t2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0005HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\b\u0010\u0014R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\n\u0010\u000e¨\u0006\""}, d2 = {"Lcom/cncverse/CastleTvProvider$SubtitleData;", "", "languageId", "", "abbreviate", "", "title", "url", "isDefault", "", "isAI", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;)V", "getLanguageId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getAbbreviate", "()Ljava/lang/String;", "getTitle", "getUrl", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;)Lcom/cncverse/CastleTvProvider$SubtitleData;", "equals", "other", "hashCode", "toString", "CastleTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SubtitleData {

        @Nullable
        private final String abbreviate;

        @Nullable
        private final Integer isAI;

        @Nullable
        private final Boolean isDefault;

        @Nullable
        private final Integer languageId;

        @Nullable
        private final String title;

        @Nullable
        private final String url;

        public SubtitleData() {
            this(null, null, null, null, null, null, 63, null);
        }

        public static /* synthetic */ SubtitleData copy$default(SubtitleData subtitleData, Integer num, String str, String str2, String str3, Boolean bool, Integer num2, int i, Object obj) {
            if ((i & 1) != 0) {
                num = subtitleData.languageId;
            }
            if ((i & 2) != 0) {
                str = subtitleData.abbreviate;
            }
            if ((i & 4) != 0) {
                str2 = subtitleData.title;
            }
            if ((i & 8) != 0) {
                str3 = subtitleData.url;
            }
            if ((i & 16) != 0) {
                bool = subtitleData.isDefault;
            }
            if ((i & 32) != 0) {
                num2 = subtitleData.isAI;
            }
            Boolean bool2 = bool;
            Integer num3 = num2;
            return subtitleData.copy(num, str, str2, str3, bool2, num3);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getLanguageId() {
            return this.languageId;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getAbbreviate() {
            return this.abbreviate;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Boolean getIsDefault() {
            return this.isDefault;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getIsAI() {
            return this.isAI;
        }

        @NotNull
        public final SubtitleData copy(@Nullable Integer languageId, @Nullable String abbreviate, @Nullable String title, @Nullable String url, @Nullable Boolean isDefault, @Nullable Integer isAI) {
            return new SubtitleData(languageId, abbreviate, title, url, isDefault, isAI);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SubtitleData)) {
                return false;
            }
            SubtitleData subtitleData = (SubtitleData) other;
            return Intrinsics.areEqual(this.languageId, subtitleData.languageId) && Intrinsics.areEqual(this.abbreviate, subtitleData.abbreviate) && Intrinsics.areEqual(this.title, subtitleData.title) && Intrinsics.areEqual(this.url, subtitleData.url) && Intrinsics.areEqual(this.isDefault, subtitleData.isDefault) && Intrinsics.areEqual(this.isAI, subtitleData.isAI);
        }

        public int hashCode() {
            return ((((((((((this.languageId == null ? 0 : this.languageId.hashCode()) * 31) + (this.abbreviate == null ? 0 : this.abbreviate.hashCode())) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.url == null ? 0 : this.url.hashCode())) * 31) + (this.isDefault == null ? 0 : this.isDefault.hashCode())) * 31) + (this.isAI != null ? this.isAI.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "SubtitleData(languageId=" + this.languageId + ", abbreviate=" + this.abbreviate + ", title=" + this.title + ", url=" + this.url + ", isDefault=" + this.isDefault + ", isAI=" + this.isAI + ')';
        }

        public SubtitleData(@Nullable Integer languageId, @Nullable String abbreviate, @Nullable String title, @Nullable String url, @Nullable Boolean isDefault, @Nullable Integer isAI) {
            this.languageId = languageId;
            this.abbreviate = abbreviate;
            this.title = title;
            this.url = url;
            this.isDefault = isDefault;
            this.isAI = isAI;
        }

        public /* synthetic */ SubtitleData(Integer num, String str, String str2, String str3, Boolean bool, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : bool, (i & 32) != 0 ? null : num2);
        }

        @Nullable
        public final Integer getLanguageId() {
            return this.languageId;
        }

        @Nullable
        public final String getAbbreviate() {
            return this.abbreviate;
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        public final Boolean isDefault() {
            return this.isDefault;
        }

        @Nullable
        public final Integer isAI() {
            return this.isAI;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getSecurityKey(kotlin.coroutines.Continuation<? super java.lang.String> r23) throws com.fasterxml.jackson.databind.RuntimeJsonMappingException {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.CastleTvProvider.getSecurityKey(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final byte[] deriveKey(String apiKeyB64) {
        byte[] apiKeyBytes = MainAPIKt.base64DecodeArray(apiKeyB64);
        byte[] bytes = this.keySupFixx.getBytes(StandardCharsets.US_ASCII);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] keyMaterial = ArraysKt.plus(apiKeyBytes, bytes);
        return keyMaterial.length < 16 ? ArraysKt.plus(keyMaterial, new byte[16 - keyMaterial.length]) : keyMaterial.length > 16 ? ArraysKt.copyOfRange(keyMaterial, 0, 16) : keyMaterial;
    }

    private final String decryptData(String encryptedB64, String apiKeyB64) {
        try {
            byte[] aesKey = deriveKey(apiKeyB64);
            byte[] encryptedData = MainAPIKt.base64DecodeArray(encryptedB64);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(aesKey);
            cipher.init(2, secretKey, ivSpec);
            byte[] decrypted = cipher.doFinal(encryptedData);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }

    @NotNull
    public List<MainPageData> getMainPage() {
        return this.mainPage;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0306 A[Catch: Exception -> 0x03a6, PHI: r51
  0x0306: PHI (r51v8 'decryptedResponse' com.cncverse.CastleTvProvider$DecryptedResponse) = 
  (r51v7 'decryptedResponse' com.cncverse.CastleTvProvider$DecryptedResponse)
  (r51v10 'decryptedResponse' com.cncverse.CastleTvProvider$DecryptedResponse)
  (r51v10 'decryptedResponse' com.cncverse.CastleTvProvider$DecryptedResponse)
 binds: [B:101:0x02da, B:105:0x02e4, B:111:0x02f1] A[DONT_GENERATE, DONT_INLINE], TryCatch #6 {Exception -> 0x03a6, blocks: (B:41:0x0129, B:54:0x01a1, B:56:0x01ab, B:62:0x01b7, B:64:0x01c1, B:66:0x01c7, B:68:0x01d1, B:70:0x01e7, B:72:0x020f, B:74:0x0221, B:75:0x022e, B:76:0x022f, B:78:0x023f, B:79:0x0251, B:81:0x0257, B:84:0x026d, B:86:0x0275, B:87:0x028e, B:89:0x0294, B:123:0x032b, B:92:0x02ad, B:94:0x02b5, B:97:0x02c3, B:117:0x0301, B:119:0x030a, B:114:0x02f5, B:116:0x02fc, B:109:0x02ea, B:118:0x0306, B:104:0x02df, B:100:0x02d3, B:126:0x033e, B:128:0x0352, B:130:0x035d, B:132:0x0367, B:134:0x036f, B:139:0x0384, B:127:0x034a, B:141:0x0390, B:143:0x039e, B:142:0x0398, B:53:0x0193, B:42:0x012c, B:44:0x0145, B:46:0x016b, B:48:0x017e, B:49:0x018b, B:50:0x018c), top: B:170:0x0129, inners: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x009c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b0 A[Catch: Exception -> 0x03ac, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x03ac, blocks: (B:29:0x0098, B:35:0x00b0), top: B:160:0x0098 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0145 A[Catch: Exception -> 0x0192, TryCatch #8 {Exception -> 0x0192, blocks: (B:42:0x012c, B:44:0x0145, B:46:0x016b, B:48:0x017e, B:49:0x018b, B:50:0x018c), top: B:173:0x012c, outer: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x018c A[Catch: Exception -> 0x0192, TRY_LEAVE, TryCatch #8 {Exception -> 0x0192, blocks: (B:42:0x012c, B:44:0x0145, B:46:0x016b, B:48:0x017e, B:49:0x018b, B:50:0x018c), top: B:173:0x012c, outer: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001e  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getMainPage(int r53, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.MainPageRequest r54, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.HomePageResponse> r55) throws com.fasterxml.jackson.databind.RuntimeJsonMappingException {
        /*
            Method dump skipped, instruction units count: 984
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.CastleTvProvider.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$0$0$0(String $coverImg, MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl($coverImg);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0088 A[Catch: Exception -> 0x0057, TRY_ENTER, TRY_LEAVE, TryCatch #2 {Exception -> 0x0057, blocks: (B:13:0x0042, B:16:0x0052, B:32:0x0088), top: B:124:0x002a }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008d A[Catch: Exception -> 0x027b, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Exception -> 0x027b, blocks: (B:30:0x0084, B:34:0x008d), top: B:120:0x0084 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x011a A[Catch: Exception -> 0x0275, TryCatch #4 {Exception -> 0x0275, blocks: (B:40:0x0103, B:42:0x010e, B:48:0x011a, B:50:0x011f, B:52:0x0125, B:54:0x012a, B:56:0x0140, B:58:0x0169, B:60:0x017c, B:61:0x0189, B:62:0x018a, B:64:0x0199, B:65:0x01ad, B:67:0x01b3, B:105:0x025b, B:70:0x01ce, B:72:0x01d6, B:75:0x01e6, B:77:0x01ec, B:78:0x01f0, B:98:0x022c, B:101:0x0236, B:95:0x0220, B:97:0x0227, B:90:0x0213, B:100:0x0232, B:85:0x0208, B:81:0x01fc, B:107:0x0266, B:108:0x026e), top: B:127:0x0103 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x011f A[Catch: Exception -> 0x0275, TryCatch #4 {Exception -> 0x0275, blocks: (B:40:0x0103, B:42:0x010e, B:48:0x011a, B:50:0x011f, B:52:0x0125, B:54:0x012a, B:56:0x0140, B:58:0x0169, B:60:0x017c, B:61:0x0189, B:62:0x018a, B:64:0x0199, B:65:0x01ad, B:67:0x01b3, B:105:0x025b, B:70:0x01ce, B:72:0x01d6, B:75:0x01e6, B:77:0x01ec, B:78:0x01f0, B:98:0x022c, B:101:0x0236, B:95:0x0220, B:97:0x0227, B:90:0x0213, B:100:0x0232, B:85:0x0208, B:81:0x01fc, B:107:0x0266, B:108:0x026e), top: B:127:0x0103 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0231 A[PHI: r36
  0x0231: PHI (r36v5 'searchUrl' java.lang.String) = (r36v4 'searchUrl' java.lang.String), (r36v8 'searchUrl' java.lang.String) binds: [B:82:0x0203, B:86:0x020d] A[DONT_GENERATE, DONT_INLINE]] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object search(@org.jetbrains.annotations.NotNull java.lang.String r38, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.SearchResponse>> r39) throws com.fasterxml.jackson.databind.RuntimeJsonMappingException {
        /*
            Method dump skipped, instruction units count: 668
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.CastleTvProvider.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit search$lambda$0$0(String $posterUrl, SearchResultItem $item, MovieSearchResponse $this$newMovieSearchResponse) {
        Integer numValueOf;
        $this$newMovieSearchResponse.setPosterUrl($posterUrl);
        Long publishTime = $item.getPublishTime();
        if (publishTime != null) {
            long timestamp = publishTime.longValue();
            numValueOf = Integer.valueOf(Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).getYear());
        } else {
            numValueOf = null;
        }
        $this$newMovieSearchResponse.setYear(numValueOf);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    /* JADX WARN: Can't wrap try/catch for region: R(11:203|204|400|205|206|404|207|(15:209|398|210|211|402|212|213|431|214|215|367|216|(1:218)(1:219)|220|221)(4:230|412|231|(4:233|(9:236|237|395|238|239|375|240|458|234)|457|243)(1:247))|286|456|(2:420|154)) */
    /* JADX WARN: Can't wrap try/catch for region: R(37:451|163|164|426|165|166|443|167|168|435|169|170|416|171|172|441|173|174|418|175|176|393|177|178|373|179|180|439|181|182|377|183|184|414|185|186|(1:188)(9:189|383|190|(7:408|192|(0)(1:195)|(1:256)(11:203|204|400|205|206|404|207|(15:209|398|210|211|402|212|213|431|214|215|367|216|(1:218)(1:219)|220|221)(4:230|412|231|(4:233|(9:236|237|395|238|239|375|240|458|234)|457|243)(1:247))|286|456|(2:420|154))|258|456|(2:420|154))|198|(3:200|201|256)(1:257)|258|456|(2:420|154))) */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x048e, code lost:
    
        if (r0.intValue() != 1) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:252:0x07d1, code lost:
    
        r50 = r3;
        r48 = r20;
        r51 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x07e2, code lost:
    
        r43 = r2;
        r50 = r3;
        r48 = r20;
        r51 = r26;
        r2 = r61;
        r1 = r31;
        r3 = r32;
        r5 = r33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:262:0x084b, code lost:
    
        r48 = r20;
        r51 = r26;
        r50 = r61;
        r45 = r15;
        r43 = r18;
        r15 = r27;
        r1 = r28;
        r28 = r7;
        r27 = r13;
        r18 = r1;
        r24 = r8;
        r17 = r16;
        r13 = r12;
        r10 = r57;
        r8 = r6;
        r6 = r4;
        r4 = r62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:266:0x087a, code lost:
    
        r48 = r20;
        r51 = r26;
        r50 = r61;
        r45 = r15;
        r43 = r18;
        r15 = r27;
        r1 = r28;
        r28 = r7;
        r27 = r13;
        r18 = r1;
        r24 = r8;
        r17 = r16;
        r13 = r12;
        r10 = r10;
        r8 = r6;
        r6 = r4;
        r4 = r62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:268:0x08aa, code lost:
    
        r48 = r20;
        r51 = r26;
        r50 = r61;
        r45 = r15;
        r43 = r18;
        r15 = r27;
        r1 = r28;
        r28 = r7;
        r27 = r13;
        r18 = r1;
        r24 = r8;
        r17 = r16;
        r13 = r12;
        r10 = r10;
        r8 = r6;
        r6 = r4;
        r4 = r62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:270:0x08da, code lost:
    
        r48 = r20;
        r51 = r26;
        r50 = r61;
        r45 = r15;
        r43 = r18;
        r15 = r27;
        r1 = r28;
        r28 = r7;
        r27 = r13;
        r18 = r17;
        r24 = r8;
        r17 = r16;
        r13 = r12;
        r10 = r10;
        r8 = r6;
        r6 = r4;
        r4 = r62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:273:0x090c, code lost:
    
        r48 = r20;
        r51 = r26;
        r50 = r61;
        r45 = r15;
        r43 = r18;
        r15 = r27;
        r1 = r28;
        r28 = r7;
        r27 = r13;
        r18 = r1;
        r24 = r8;
        r17 = r16;
        r13 = r12;
        r10 = r10;
        r8 = r6;
        r6 = r4;
        r4 = r62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x093a, code lost:
    
        r48 = r20;
        r51 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x094b, code lost:
    
        r48 = r20;
        r51 = r26;
        r14 = r41;
        r50 = r61;
        r45 = r15;
        r43 = r18;
        r15 = r27;
        r28 = r7;
        r27 = r13;
        r18 = r17;
        r24 = r8;
        r17 = r16;
        r13 = r12;
        r10 = r10;
        r8 = r6;
        r6 = r4;
        r4 = r62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:279:0x097c, code lost:
    
        r57 = r10;
        r42 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x0988, code lost:
    
        r57 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:283:0x0992, code lost:
    
        r57 = r10;
        r27 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:284:0x099c, code lost:
    
        r48 = r20;
        r51 = r26;
        r14 = r41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:285:0x09a4, code lost:
    
        r50 = r61;
        r45 = r15;
        r43 = r18;
        r15 = r27;
        r28 = r7;
        r27 = r13;
        r18 = r17;
        r24 = r8;
        r17 = r16;
        r13 = r12;
        r10 = r10;
        r8 = r6;
        r6 = r4;
        r4 = r62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:449:0x09c1, code lost:
    
        r48 = r20;
        r51 = r26;
        r50 = r61;
        r45 = r15;
        r43 = r18;
        r15 = r27;
        r1 = r28;
        r28 = r7;
        r27 = r13;
        r18 = r17;
        r24 = r8;
        r17 = r16;
        r13 = r12;
        r10 = r10;
        r8 = r6;
        r6 = r4;
        r4 = r62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:450:0x09c1, code lost:
    
        r50 = r61;
        r45 = r15;
        r43 = r18;
        r15 = r27;
        r28 = r7;
        r27 = r13;
        r18 = r17;
        r24 = r8;
        r17 = r16;
        r13 = r12;
        r10 = r57;
        r8 = r6;
        r6 = r4;
        r4 = r62;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:119:0x0488, B:145:0x04c0], limit reached: 453 */
    /* JADX WARN: Path cross not found for [B:120:0x0489, B:119:0x0488], limit reached: 453 */
    /* JADX WARN: Path cross not found for [B:127:0x0498, B:126:0x0497], limit reached: 453 */
    /* JADX WARN: Path cross not found for [B:133:0x04a7, B:132:0x04a6], limit reached: 453 */
    /* JADX WARN: Path cross not found for [B:145:0x04c0, B:126:0x0497], limit reached: 453 */
    /* JADX WARN: Path cross not found for [B:145:0x04c0, B:132:0x04a6], limit reached: 453 */
    /* JADX WARN: Path cross not found for [B:198:0x0663, B:408:0x0644], limit reached: 453 */
    /* JADX WARN: Path cross not found for [B:408:0x0644, B:198:0x0663], limit reached: 453 */
    /* JADX WARN: Path cross not found for [B:433:0x02f2, B:58:0x0304], limit reached: 453 */
    /* JADX WARN: Path cross not found for [B:58:0x0304, B:433:0x02f2], limit reached: 453 */
    /* JADX WARN: Removed duplicated region for block: B:156:0x051b A[Catch: Exception -> 0x0a58, TryCatch #28 {Exception -> 0x0a58, blocks: (B:154:0x0515, B:156:0x051b, B:158:0x0529, B:161:0x0547, B:163:0x054f), top: B:420:0x0515 }] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0667  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0801 A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0b90 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0b91  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0bd7 A[Catch: Exception -> 0x0cdf, TRY_ENTER, TRY_LEAVE, TryCatch #11 {Exception -> 0x0cdf, blocks: (B:104:0x0407, B:117:0x047d, B:320:0x0bd7, B:328:0x0bf3, B:333:0x0c17, B:120:0x0489), top: B:387:0x0407 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0266 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:408:0x0644 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x04c5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:433:0x02f2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0307 A[ADDED_TO_REGION, REMOVE, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0308 A[Catch: Exception -> 0x0ceb, TRY_ENTER, TryCatch #3 {Exception -> 0x0ceb, blocks: (B:50:0x02e5, B:61:0x0308, B:64:0x0310, B:74:0x0375, B:80:0x038e, B:84:0x039a, B:88:0x03a6, B:93:0x03d8, B:335:0x0ca3), top: B:371:0x02e5 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Type inference failed for: r0v130 */
    /* JADX WARN: Type inference failed for: r0v131, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v134 */
    /* JADX WARN: Type inference failed for: r11v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v13 */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v20 */
    /* JADX WARN: Type inference failed for: r11v21 */
    /* JADX WARN: Type inference failed for: r11v22 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r1v60, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r24v1 */
    /* JADX WARN: Type inference failed for: r24v6, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r24v7 */
    /* JADX WARN: Type inference failed for: r2v14, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r3v42 */
    /* JADX WARN: Type inference failed for: r3v43, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v46 */
    /* JADX WARN: Type inference failed for: r50v0 */
    /* JADX WARN: Type inference failed for: r50v1, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r50v2 */
    /* JADX WARN: Type inference failed for: r52v0 */
    /* JADX WARN: Type inference failed for: r52v1, types: [com.cncverse.CastleTvProvider$ApiEpisode, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r52v2 */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v33 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:189:0x060f -> B:383:0x0636). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:262:0x084b -> B:286:0x09c1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:266:0x087a -> B:286:0x09c1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:268:0x08aa -> B:286:0x09c1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:270:0x08da -> B:286:0x09c1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:277:0x094b -> B:286:0x09c1). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull java.lang.String r61, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r62) throws com.fasterxml.jackson.databind.RuntimeJsonMappingException {
        /*
            Method dump skipped, instruction units count: 3420
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.CastleTvProvider.load(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit load$lambda$2$0(ApiEpisode $episode, List $allEpisodes, int $seasonNumber, Episode $this$newEpisode) {
        String title = $episode.getTitle();
        if (title == null) {
            StringBuilder sbAppend = new StringBuilder().append("Episode ");
            Integer number = $episode.getNumber();
            title = sbAppend.append(number != null ? number.intValue() : $allEpisodes.size() + 1).toString();
        }
        $this$newEpisode.setName(title);
        $this$newEpisode.setSeason(Integer.valueOf($seasonNumber));
        Integer number2 = $episode.getNumber();
        if (number2 == null) {
            number2 = Integer.valueOf($allEpisodes.size() + 1);
        }
        $this$newEpisode.setEpisode(number2);
        $this$newEpisode.setPosterUrl($episode.getCoverImage());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit load$lambda$3$0(ApiEpisode $episode, int $index, MovieDetails $details, Episode $this$newEpisode) {
        String title = $episode.getTitle();
        if (title == null) {
            StringBuilder sbAppend = new StringBuilder().append("Episode ");
            Integer number = $episode.getNumber();
            title = sbAppend.append(number != null ? number.intValue() : $index + 1).toString();
        }
        $this$newEpisode.setName(title);
        $this$newEpisode.setSeason($details.getSeasonNumber());
        Integer number2 = $episode.getNumber();
        if (number2 == null) {
            number2 = Integer.valueOf($index + 1);
        }
        $this$newEpisode.setEpisode(number2);
        $this$newEpisode.setPosterUrl($episode.getCoverImage());
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.cncverse.CastleTvProvider$load$4, reason: invalid class name */
    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CastleTvProvider$load$4", f = "CastleTvProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<TvSeriesLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<ActorData> $actors;
        final /* synthetic */ String $backgroundPosterUrl;
        final /* synthetic */ MovieDetails $details;
        final /* synthetic */ String $plot;
        final /* synthetic */ String $posterUrl;
        final /* synthetic */ Integer $rating;
        final /* synthetic */ List<SearchResponse> $recommendations;
        final /* synthetic */ List<String> $tags;
        final /* synthetic */ Integer $year;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(String str, String str2, String str3, Integer num, Integer num2, List<String> list, List<ActorData> list2, List<? extends SearchResponse> list3, MovieDetails movieDetails, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$posterUrl = str;
            this.$backgroundPosterUrl = str2;
            this.$plot = str3;
            this.$year = num;
            this.$rating = num2;
            this.$tags = list;
            this.$actors = list2;
            this.$recommendations = list3;
            this.$details = movieDetails;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass4 = new AnonymousClass4(this.$posterUrl, this.$backgroundPosterUrl, this.$plot, this.$year, this.$rating, this.$tags, this.$actors, this.$recommendations, this.$details, continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        public final Object invoke(TvSeriesLoadResponse tvSeriesLoadResponse, Continuation<? super Unit> continuation) {
            return create(tvSeriesLoadResponse, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            ShowStatus showStatus;
            ApiEpisode apiEpisode;
            Integer duration;
            TvSeriesLoadResponse $this$newTvSeriesLoadResponse = (TvSeriesLoadResponse) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newTvSeriesLoadResponse.setPosterUrl(this.$posterUrl);
                    $this$newTvSeriesLoadResponse.setBackgroundPosterUrl(this.$backgroundPosterUrl);
                    $this$newTvSeriesLoadResponse.setPlot(this.$plot);
                    $this$newTvSeriesLoadResponse.setYear(this.$year);
                    $this$newTvSeriesLoadResponse.setScore(Score.Companion.from(this.$rating, 1000));
                    $this$newTvSeriesLoadResponse.setTags(this.$tags);
                    $this$newTvSeriesLoadResponse.setActors(this.$actors);
                    $this$newTvSeriesLoadResponse.setRecommendations(this.$recommendations);
                    List<ApiEpisode> episodes = this.$details.getEpisodes();
                    $this$newTvSeriesLoadResponse.setDuration((episodes == null || (apiEpisode = (ApiEpisode) CollectionsKt.firstOrNull(episodes)) == null || (duration = apiEpisode.getDuration()) == null) ? null : Boxing.boxInt(duration.intValue() / 60));
                    String seasonDescription = this.$details.getSeasonDescription();
                    boolean z = false;
                    if (seasonDescription != null && StringsKt.contains(seasonDescription, "season", true)) {
                        z = true;
                    }
                    if (z) {
                        showStatus = ShowStatus.Ongoing;
                    } else {
                        showStatus = ShowStatus.Completed;
                    }
                    $this$newTvSeriesLoadResponse.setShowStatus(showStatus);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.CastleTvProvider$load$5, reason: invalid class name */
    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CastleTvProvider$load$5", f = "CastleTvProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass5 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<ActorData> $actors;
        final /* synthetic */ String $backgroundPosterUrl;
        final /* synthetic */ ApiEpisode $episode;
        final /* synthetic */ String $plot;
        final /* synthetic */ String $posterUrl;
        final /* synthetic */ Integer $rating;
        final /* synthetic */ List<SearchResponse> $recommendations;
        final /* synthetic */ List<String> $tags;
        final /* synthetic */ Integer $year;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(String str, String str2, String str3, Integer num, Integer num2, List<String> list, List<ActorData> list2, List<? extends SearchResponse> list3, ApiEpisode apiEpisode, Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
            this.$posterUrl = str;
            this.$backgroundPosterUrl = str2;
            this.$plot = str3;
            this.$year = num;
            this.$rating = num2;
            this.$tags = list;
            this.$actors = list2;
            this.$recommendations = list3;
            this.$episode = apiEpisode;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass5 = new AnonymousClass5(this.$posterUrl, this.$backgroundPosterUrl, this.$plot, this.$year, this.$rating, this.$tags, this.$actors, this.$recommendations, this.$episode, continuation);
            anonymousClass5.L$0 = obj;
            return anonymousClass5;
        }

        public final Object invoke(MovieLoadResponse movieLoadResponse, Continuation<? super Unit> continuation) {
            return create(movieLoadResponse, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Integer duration;
            MovieLoadResponse $this$newMovieLoadResponse = (MovieLoadResponse) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newMovieLoadResponse.setPosterUrl(this.$posterUrl);
                    $this$newMovieLoadResponse.setBackgroundPosterUrl(this.$backgroundPosterUrl);
                    $this$newMovieLoadResponse.setPlot(this.$plot);
                    $this$newMovieLoadResponse.setYear(this.$year);
                    $this$newMovieLoadResponse.setScore(Score.Companion.from(this.$rating, 1000));
                    $this$newMovieLoadResponse.setTags(this.$tags);
                    $this$newMovieLoadResponse.setActors(this.$actors);
                    $this$newMovieLoadResponse.setRecommendations(this.$recommendations);
                    ApiEpisode apiEpisode = this.$episode;
                    $this$newMovieLoadResponse.setDuration((apiEpisode == null || (duration = apiEpisode.getDuration()) == null) ? null : Boxing.boxInt(duration.intValue() / 60));
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:236:0x0d00 -> B:439:0x1619). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:263:0x0dd1 -> B:439:0x1619). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:265:0x0deb -> B:439:0x1619). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:267:0x0e07 -> B:439:0x1619). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:269:0x0e25 -> B:439:0x1619). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:325:0x101a -> B:845:0x1046). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:354:0x1155 -> B:810:0x1189). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:363:0x121b -> B:364:0x1244). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:380:0x1331 -> B:439:0x1619). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:382:0x1352 -> B:439:0x1619). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:384:0x1370 -> B:439:0x1619). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:386:0x1396 -> B:439:0x1619). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:388:0x13bc -> B:439:0x1619). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:390:0x13e4 -> B:439:0x1619). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:403:0x1473 -> B:950:0x0ba4). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:411:0x14d4 -> B:439:0x1619). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:413:0x14fe -> B:439:0x1619). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:415:0x1528 -> B:439:0x1619). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:417:0x154a -> B:439:0x1619). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:419:0x156c -> B:439:0x1619). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:458:0x16ac -> B:904:0x16d5). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:502:0x184b -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:530:0x1928 -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:532:0x1948 -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:534:0x196a -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:557:0x1a19 -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:559:0x1a3b -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:561:0x1a5d -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:588:0x1b87 -> B:902:0x1bad). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:621:0x1cd1 -> B:946:0x1d14). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:632:0x1da6 -> B:633:0x1dd8). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:649:0x1eef -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:651:0x1f13 -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:653:0x1f33 -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:655:0x1f55 -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:657:0x1f79 -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:659:0x1f9d -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:661:0x1fc1 -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:670:0x2003 -> B:904:0x16d5). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:675:0x2033 -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:677:0x2053 -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:679:0x2076 -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:681:0x209b -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:683:0x20c2 -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:685:0x20e5 -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:687:0x210a -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:689:0x2131 -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:691:0x2158 -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:698:0x21b4 -> B:709:0x2238). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:700:0x21db -> B:709:0x2238). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: DebugInfoApplyVisitor
        java.lang.StackOverflowError
        	at jadx.core.dex.visitors.typeinference.TypeCompare.compareObjectsNoPreCheck(TypeCompare.java:162)
        	at jadx.core.dex.visitors.typeinference.TypeCompare.compareTypes(TypeCompare.java:97)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:139)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:113)
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
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
        */
    @org.jetbrains.annotations.Nullable
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r83, boolean r84, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r85, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r86, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r87) {
        /*
            Method dump skipped, instruction units count: 9120
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.CastleTvProvider.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadLinks$lambda$0$0(Context $_ctx) {
        Toast.makeText($_ctx, "⚠️(Opening ads) Subscription expired. If you have renewed your subscription, please re-verify it in Subscription Manager.", 1).show();
    }

    /* JADX INFO: renamed from: com.cncverse.CastleTvProvider$loadLinks$3, reason: invalid class name */
    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CastleTvProvider$loadLinks$3", f = "CastleTvProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $resolution;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(int i, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$resolution = i;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass3 = CastleTvProvider.this.new AnonymousClass3(this.$resolution, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
            return create(extractorLink, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            int i;
            ExtractorLink $this$newExtractorLink = (ExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newExtractorLink.setHeaders(MapsKt.mapOf(TuplesKt.to("Referer", CastleTvProvider.this.getMainUrl())));
                    switch (this.$resolution) {
                        case 1:
                            i = 480;
                            break;
                        case 2:
                            i = 720;
                            break;
                        case 3:
                            i = 1080;
                            break;
                        default:
                            i = this.$resolution * 240;
                            break;
                    }
                    $this$newExtractorLink.setQuality(i);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.CastleTvProvider$loadLinks$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CastleTvProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CastleTvProvider$loadLinks$5", f = "CastleTvProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C00035 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $resolution;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00035(int i, Continuation<? super C00035> continuation) {
            super(2, continuation);
            this.$resolution = i;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> c00035 = CastleTvProvider.this.new C00035(this.$resolution, continuation);
            c00035.L$0 = obj;
            return c00035;
        }

        public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
            return create(extractorLink, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            int i;
            ExtractorLink $this$newExtractorLink = (ExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newExtractorLink.setHeaders(MapsKt.mapOf(TuplesKt.to("Referer", CastleTvProvider.this.getMainUrl())));
                    switch (this.$resolution) {
                        case 1:
                            i = 480;
                            break;
                        case 2:
                            i = 720;
                            break;
                        case 3:
                            i = 1080;
                            break;
                        default:
                            i = this.$resolution * 240;
                            break;
                    }
                    $this$newExtractorLink.setQuality(i);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */
}
