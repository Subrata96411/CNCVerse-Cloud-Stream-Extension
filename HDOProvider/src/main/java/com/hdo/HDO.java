package com.hdo;

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
import com.lagradost.cloudstream3.HomePageResponse;
import com.lagradost.cloudstream3.MainPageRequest;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.metaproviders.TmdbLink;
import com.lagradost.cloudstream3.metaproviders.TmdbProvider;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
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
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: HDO.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nHDO.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HDO.kt\ncom/hdo/HDO\n+ 2 AppUtils.kt\ncom/lagradost/cloudstream3/utils/AppUtils\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n*L\n1#1,489:1\n63#2:490\n64#2,15:492\n63#2:510\n64#2,15:512\n1#3:491\n1#3:509\n1#3:511\n1#3:529\n50#4:507\n43#4:508\n50#4:527\n43#4:528\n*S KotlinDebug\n*F\n+ 1 HDO.kt\ncom/hdo/HDO\n*L\n70#1:490\n70#1:492,15\n184#1:510\n184#1:512,15\n70#1:491\n184#1:511\n70#1:507\n70#1:508\n184#1:527\n184#1:528\n*E\n"})
public final class HDO extends TmdbProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull

    @Nullable
    private static Context cont;

    @NotNull
    private String name = "HDO";
    private final boolean hasMainPage = true;

    @NotNull
    private String lang = "ta";
    private final boolean instantLinkLoading = true;
    private final boolean useMetaLoadResponse = true;
    private final boolean hasQuickSearch = true;

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(new TvType[]{TvType.Movie, TvType.TvSeries});

    /* JADX INFO: renamed from: com.hdo.HDO$callHulaApiServer$1, reason: invalid class name */
    /* JADX INFO: compiled from: HDO.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.hdo.HDO", f = "HDO.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2}, l = {178, 203, 220}, m = "callHulaApiServer", n = {"mediaData", "callback", "apiUrl", "mediaData", "callback", "apiUrl", "response", "apiResponse", "result", "referer", "headers", "track", "quality", "mediaData", "callback", "apiUrl", "response", "apiResponse", "result", "referer", "headers"}, nl = {179, 202, 219}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "L$7", "L$8", "L$10", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "L$7", "L$8"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
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

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HDO.this.callHulaApiServer(null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.hdo.HDO$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HDO.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.hdo.HDO", f = "HDO.kt", i = {0, 0, 0, 0, 0}, l = {75}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "mediaData", "isCasting"}, nl = {103}, s = {"L$0", "L$1", "L$2", "L$3", "Z$0"}, v = 2)
    static final class C00001 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C00001(Continuation<? super C00001> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HDO.this.loadLinks(null, false, null, null, (Continuation) this);
        }
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

    public boolean getInstantLinkLoading() {
        return this.instantLinkLoading;
    }

    public boolean getUseMetaLoadResponse() {
        return this.useMetaLoadResponse;
    }

    public boolean getHasQuickSearch() {
        return this.hasQuickSearch;
    }

    @NotNull
    public Set<TvType> getSupportedTypes() {
        return this.supportedTypes;
    }

    /* JADX INFO: compiled from: HDO.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/hdo/HDO$Companion;", "", "<init>", "()V", "cont", "Landroid/content/Context;", "getCont", "()Landroid/content/Context;", "setCont", "(Landroid/content/Context;)V", "OMG10", "", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "HDOProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getCont() {
            return HDO.cont;
        }

        public final void setCont(@Nullable Context context) {
            HDO.cont = context;
        }
    }

    @Nullable
    public Object getMainPage(int page, @NotNull MainPageRequest request, @NotNull Continuation<? super HomePageResponse> continuation) {
        showTelegramPopup();
        showSubscriptionPopupIfNeeded();
        return super.getMainPage(page, request, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x01bd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r22, boolean r23, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r24, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r25, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r26) {
        /*
            Method dump skipped, instruction units count: 466
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hdo.HDO.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadLinks$lambda$0$0(Context $_ctx) {
        Toast.makeText($_ctx, "⚠️(Opening ads) Subscription expired. If you have renewed your subscription, please re-verify it in Subscription Manager.", 1).show();
    }

    /* JADX INFO: renamed from: com.hdo.HDO$loadLinks$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HDO.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.hdo.HDO$loadLinks$3", f = "HDO.kt", i = {}, l = {76, 83, 92}, m = "invokeSuspend", n = {}, nl = {83, 91, 94}, s = {}, v = 2)
    static final class C00013 extends SuspendLambda implements Function1<Continuation<? super Integer>, Object> {
        final /* synthetic */ Function1<ExtractorLink, Unit> $callback;
        final /* synthetic */ HulaMovieInfo $mediaData;
        final /* synthetic */ Function1<SubtitleFile, Unit> $subtitleCallback;
        int label;
        final /* synthetic */ HDO this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00013(HulaMovieInfo hulaMovieInfo, Function1<? super SubtitleFile, Unit> function1, HDO hdo, Function1<? super ExtractorLink, Unit> function12, Continuation<? super C00013> continuation) {
            super(1, continuation);
            this.$mediaData = hulaMovieInfo;
            this.$subtitleCallback = function1;
            this.this$0 = hdo;
            this.$callback = function12;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C00013(this.$mediaData, this.$subtitleCallback, this.this$0, this.$callback, continuation);
        }

        public final Object invoke(Continuation<? super Integer> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0068 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0080 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0090  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r9.label
                java.lang.String r2 = "HDOProvider"
                switch(r1) {
                    case 0: goto L20;
                    case 1: goto L1c;
                    case 2: goto L18;
                    case 3: goto L13;
                    default: goto Lb;
                }
            Lb:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L13:
                kotlin.ResultKt.throwOnFailure(r10)
                r1 = r10
                goto L81
            L18:
                kotlin.ResultKt.throwOnFailure(r10)
                goto L69
            L1c:
                kotlin.ResultKt.throwOnFailure(r10)
                goto L46
            L20:
                kotlin.ResultKt.throwOnFailure(r10)
                com.hdo.SubUtils r3 = com.hdo.SubUtils.INSTANCE
                com.hdo.HDO$HulaMovieInfo r1 = r9.$mediaData
                java.lang.String r4 = r1.getImdbId()
                com.hdo.HDO$HulaMovieInfo r1 = r9.$mediaData
                java.lang.Integer r5 = r1.getSeason()
                com.hdo.HDO$HulaMovieInfo r1 = r9.$mediaData
                java.lang.Integer r6 = r1.getEpisode()
                kotlin.jvm.functions.Function1<com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r7 = r9.$subtitleCallback
                r8 = r9
                kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                r1 = 1
                r9.label = r1
                java.lang.Object r1 = r3.invokeWyZIESUBAPI(r4, r5, r6, r7, r8)
                if (r1 != r0) goto L46
                return r0
            L46:
                com.hdo.SubUtils r3 = com.hdo.SubUtils.INSTANCE
                com.hdo.HDO$HulaMovieInfo r1 = r9.$mediaData
                java.lang.String r4 = r1.getImdbId()
                com.hdo.HDO$HulaMovieInfo r1 = r9.$mediaData
                java.lang.Integer r5 = r1.getSeason()
                com.hdo.HDO$HulaMovieInfo r1 = r9.$mediaData
                java.lang.Integer r6 = r1.getEpisode()
                kotlin.jvm.functions.Function1<com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r7 = r9.$subtitleCallback
                r8 = r9
                kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                r1 = 2
                r9.label = r1
                java.lang.Object r1 = r3.invokeSubtitleAPI(r4, r5, r6, r7, r8)
                if (r1 != r0) goto L69
                return r0
            L69:
                java.lang.String r1 = "Calling Hula API server..."
                android.util.Log.d(r2, r1)
                com.hdo.HDO r1 = r9.this$0
                com.hdo.HDO$HulaMovieInfo r3 = r9.$mediaData
                kotlin.jvm.functions.Function1<com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r4 = r9.$callback
                r5 = r9
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r6 = 3
                r9.label = r6
                java.lang.Object r1 = com.hdo.HDO.access$callHulaApiServer(r1, r3, r4, r5)
                if (r1 != r0) goto L81
                return r0
            L81:
                java.lang.Boolean r1 = (java.lang.Boolean) r1
                boolean r0 = r1.booleanValue()
                if (r0 == 0) goto L90
                java.lang.String r1 = "Successfully loaded video links from Hula API"
                int r1 = android.util.Log.d(r2, r1)
                goto L96
            L90:
                java.lang.String r1 = "Failed to load video links from Hula API"
                int r1 = android.util.Log.w(r2, r1)
            L96:
                java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hdo.HDO.C00013.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: compiled from: HDO.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jb\u0010\u001e\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0013\u0010\u0010R\u001a\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0014\u0010\u0010R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0015\u0010\u0010R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006%"}, d2 = {"Lcom/hdo/HDO$HulaMovieInfo;", "", "imdbId", "", "tmdbId", "", "title", "year", "season", "episode", "type", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)V", "getImdbId", "()Ljava/lang/String;", "getTmdbId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitle", "getYear", "getSeason", "getEpisode", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/hdo/HDO$HulaMovieInfo;", "equals", "", "other", "hashCode", "toString", "HDOProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class HulaMovieInfo {

        @JsonProperty("episode")
        @Nullable
        private final Integer episode;

        @JsonProperty("imdbId")
        @Nullable
        private final String imdbId;

        @JsonProperty("season")
        @Nullable
        private final Integer season;

        @JsonProperty("title")
        @Nullable
        private final String title;

        @JsonProperty("tmdbId")
        @Nullable
        private final Integer tmdbId;

        @JsonProperty("type")
        @Nullable
        private final String type;

        @JsonProperty("year")
        @Nullable
        private final Integer year;

        public HulaMovieInfo() {
            this(null, null, null, null, null, null, null, 127, null);
        }

        public static /* synthetic */ HulaMovieInfo copy$default(HulaMovieInfo hulaMovieInfo, String str, Integer num, String str2, Integer num2, Integer num3, Integer num4, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = hulaMovieInfo.imdbId;
            }
            if ((i & 2) != 0) {
                num = hulaMovieInfo.tmdbId;
            }
            if ((i & 4) != 0) {
                str2 = hulaMovieInfo.title;
            }
            if ((i & 8) != 0) {
                num2 = hulaMovieInfo.year;
            }
            if ((i & 16) != 0) {
                num3 = hulaMovieInfo.season;
            }
            if ((i & 32) != 0) {
                num4 = hulaMovieInfo.episode;
            }
            if ((i & 64) != 0) {
                str3 = hulaMovieInfo.type;
            }
            Integer num5 = num4;
            String str4 = str3;
            Integer num6 = num3;
            String str5 = str2;
            return hulaMovieInfo.copy(str, num, str5, num2, num6, num5, str4);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getImdbId() {
            return this.imdbId;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getTmdbId() {
            return this.tmdbId;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getSeason() {
            return this.season;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getEpisode() {
            return this.episode;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getType() {
            return this.type;
        }

        @NotNull
        public final HulaMovieInfo copy(@JsonProperty("imdbId") @Nullable String imdbId, @JsonProperty("tmdbId") @Nullable Integer tmdbId, @JsonProperty("title") @Nullable String title, @JsonProperty("year") @Nullable Integer year, @JsonProperty("season") @Nullable Integer season, @JsonProperty("episode") @Nullable Integer episode, @JsonProperty("type") @Nullable String type) {
            return new HulaMovieInfo(imdbId, tmdbId, title, year, season, episode, type);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HulaMovieInfo)) {
                return false;
            }
            HulaMovieInfo hulaMovieInfo = (HulaMovieInfo) other;
            return Intrinsics.areEqual(this.imdbId, hulaMovieInfo.imdbId) && Intrinsics.areEqual(this.tmdbId, hulaMovieInfo.tmdbId) && Intrinsics.areEqual(this.title, hulaMovieInfo.title) && Intrinsics.areEqual(this.year, hulaMovieInfo.year) && Intrinsics.areEqual(this.season, hulaMovieInfo.season) && Intrinsics.areEqual(this.episode, hulaMovieInfo.episode) && Intrinsics.areEqual(this.type, hulaMovieInfo.type);
        }

        public int hashCode() {
            return ((((((((((((this.imdbId == null ? 0 : this.imdbId.hashCode()) * 31) + (this.tmdbId == null ? 0 : this.tmdbId.hashCode())) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.year == null ? 0 : this.year.hashCode())) * 31) + (this.season == null ? 0 : this.season.hashCode())) * 31) + (this.episode == null ? 0 : this.episode.hashCode())) * 31) + (this.type != null ? this.type.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "HulaMovieInfo(imdbId=" + this.imdbId + ", tmdbId=" + this.tmdbId + ", title=" + this.title + ", year=" + this.year + ", season=" + this.season + ", episode=" + this.episode + ", type=" + this.type + ')';
        }

        public HulaMovieInfo(@JsonProperty("imdbId") @Nullable String imdbId, @JsonProperty("tmdbId") @Nullable Integer tmdbId, @JsonProperty("title") @Nullable String title, @JsonProperty("year") @Nullable Integer year, @JsonProperty("season") @Nullable Integer season, @JsonProperty("episode") @Nullable Integer episode, @JsonProperty("type") @Nullable String type) {
            this.imdbId = imdbId;
            this.tmdbId = tmdbId;
            this.title = title;
            this.year = year;
            this.season = season;
            this.episode = episode;
            this.type = type;
        }

        public /* synthetic */ HulaMovieInfo(String str, Integer num, String str2, Integer num2, Integer num3, Integer num4, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : num3, (i & 32) != 0 ? null : num4, (i & 64) != 0 ? null : str3);
        }

        @Nullable
        public final String getImdbId() {
            return this.imdbId;
        }

        @Nullable
        public final Integer getTmdbId() {
            return this.tmdbId;
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final Integer getYear() {
            return this.year;
        }

        @Nullable
        public final Integer getSeason() {
            return this.season;
        }

        @Nullable
        public final Integer getEpisode() {
            return this.episode;
        }

        @Nullable
        public final String getType() {
            return this.type;
        }
    }

    /* JADX INFO: compiled from: HDO.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J/\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/hdo/HDO$HulaApiResponse;", "", "query", "Lcom/hdo/HDO$HulaMovieInfo;", "count", "", "results", "", "Lcom/hdo/HDO$HulaResult;", "<init>", "(Lcom/hdo/HDO$HulaMovieInfo;ILjava/util/List;)V", "getQuery", "()Lcom/hdo/HDO$HulaMovieInfo;", "getCount", "()I", "getResults", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "HDOProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class HulaApiResponse {

        @JsonProperty("count")
        private final int count;

        @JsonProperty("query")
        @Nullable
        private final HulaMovieInfo query;

        @JsonProperty("results")
        @NotNull
        private final List<HulaResult> results;

        public HulaApiResponse() {
            this(null, 0, null, 7, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ HulaApiResponse copy$default(HulaApiResponse hulaApiResponse, HulaMovieInfo hulaMovieInfo, int i, List list, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                hulaMovieInfo = hulaApiResponse.query;
            }
            if ((i2 & 2) != 0) {
                i = hulaApiResponse.count;
            }
            if ((i2 & 4) != 0) {
                list = hulaApiResponse.results;
            }
            return hulaApiResponse.copy(hulaMovieInfo, i, list);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final HulaMovieInfo getQuery() {
            return this.query;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getCount() {
            return this.count;
        }

        @NotNull
        public final List<HulaResult> component3() {
            return this.results;
        }

        @NotNull
        public final HulaApiResponse copy(@JsonProperty("query") @Nullable HulaMovieInfo query, @JsonProperty("count") int count, @JsonProperty("results") @NotNull List<HulaResult> results) {
            return new HulaApiResponse(query, count, results);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HulaApiResponse)) {
                return false;
            }
            HulaApiResponse hulaApiResponse = (HulaApiResponse) other;
            return Intrinsics.areEqual(this.query, hulaApiResponse.query) && this.count == hulaApiResponse.count && Intrinsics.areEqual(this.results, hulaApiResponse.results);
        }

        public int hashCode() {
            return ((((this.query == null ? 0 : this.query.hashCode()) * 31) + this.count) * 31) + this.results.hashCode();
        }

        @NotNull
        public String toString() {
            return "HulaApiResponse(query=" + this.query + ", count=" + this.count + ", results=" + this.results + ')';
        }

        public HulaApiResponse(@JsonProperty("query") @Nullable HulaMovieInfo query, @JsonProperty("count") int count, @JsonProperty("results") @NotNull List<HulaResult> list) {
            this.query = query;
            this.count = count;
            this.results = list;
        }

        public /* synthetic */ HulaApiResponse(HulaMovieInfo hulaMovieInfo, int i, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? null : hulaMovieInfo, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? CollectionsKt.emptyList() : list);
        }

        @Nullable
        public final HulaMovieInfo getQuery() {
            return this.query;
        }

        public final int getCount() {
            return this.count;
        }

        @NotNull
        public final List<HulaResult> getResults() {
            return this.results;
        }
    }

    /* JADX INFO: compiled from: HDO.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Be\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0003\u0010\b\u001a\u00020\u0003\u0012\u0014\b\u0003\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n\u0012\u000e\b\u0003\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u0015\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\r0\fHÆ\u0003Jg\u0010\"\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\u00032\u0014\b\u0003\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n2\u000e\b\u0003\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fHÆ\u0001J\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010&\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010'\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\"\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006("}, d2 = {"Lcom/hdo/HDO$HulaResult;", "", "provider", "", "host", "type", "quality", "", "url", "headers", "", "tracks", "", "Lcom/hdo/HDO$HulaTrack;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;Ljava/util/List;)V", "getProvider", "()Ljava/lang/String;", "getHost", "getType", "getQuality", "()I", "getUrl", "getHeaders", "()Ljava/util/Map;", "getTracks", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "HDOProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class HulaResult {

        @JsonProperty("headers")
        @NotNull
        private final Map<String, String> headers;

        @JsonProperty("host")
        @Nullable
        private final String host;

        @JsonProperty("provider")
        @Nullable
        private final String provider;

        @JsonProperty("quality")
        private final int quality;

        @JsonProperty("tracks")
        @NotNull
        private final List<HulaTrack> tracks;

        @JsonProperty("type")
        @Nullable
        private final String type;

        @JsonProperty("url")
        @NotNull
        private final String url;

        public HulaResult() {
            this(null, null, null, 0, null, null, null, 127, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ HulaResult copy$default(HulaResult hulaResult, String str, String str2, String str3, int i, String str4, Map map, List list, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = hulaResult.provider;
            }
            if ((i2 & 2) != 0) {
                str2 = hulaResult.host;
            }
            if ((i2 & 4) != 0) {
                str3 = hulaResult.type;
            }
            if ((i2 & 8) != 0) {
                i = hulaResult.quality;
            }
            if ((i2 & 16) != 0) {
                str4 = hulaResult.url;
            }
            if ((i2 & 32) != 0) {
                map = hulaResult.headers;
            }
            if ((i2 & 64) != 0) {
                list = hulaResult.tracks;
            }
            Map map2 = map;
            List list2 = list;
            String str5 = str4;
            String str6 = str3;
            return hulaResult.copy(str, str2, str6, i, str5, map2, list2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getProvider() {
            return this.provider;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getHost() {
            return this.host;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getQuality() {
            return this.quality;
        }

        @NotNull
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @NotNull
        public final Map<String, String> component6() {
            return this.headers;
        }

        @NotNull
        public final List<HulaTrack> component7() {
            return this.tracks;
        }

        @NotNull
        public final HulaResult copy(@JsonProperty("provider") @Nullable String provider, @JsonProperty("host") @Nullable String host, @JsonProperty("type") @Nullable String type, @JsonProperty("quality") int quality, @JsonProperty("url") @NotNull String url, @JsonProperty("headers") @NotNull Map<String, String> headers, @JsonProperty("tracks") @NotNull List<HulaTrack> tracks) {
            return new HulaResult(provider, host, type, quality, url, headers, tracks);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HulaResult)) {
                return false;
            }
            HulaResult hulaResult = (HulaResult) other;
            return Intrinsics.areEqual(this.provider, hulaResult.provider) && Intrinsics.areEqual(this.host, hulaResult.host) && Intrinsics.areEqual(this.type, hulaResult.type) && this.quality == hulaResult.quality && Intrinsics.areEqual(this.url, hulaResult.url) && Intrinsics.areEqual(this.headers, hulaResult.headers) && Intrinsics.areEqual(this.tracks, hulaResult.tracks);
        }

        public int hashCode() {
            return ((((((((((((this.provider == null ? 0 : this.provider.hashCode()) * 31) + (this.host == null ? 0 : this.host.hashCode())) * 31) + (this.type != null ? this.type.hashCode() : 0)) * 31) + this.quality) * 31) + this.url.hashCode()) * 31) + this.headers.hashCode()) * 31) + this.tracks.hashCode();
        }

        @NotNull
        public String toString() {
            return "HulaResult(provider=" + this.provider + ", host=" + this.host + ", type=" + this.type + ", quality=" + this.quality + ", url=" + this.url + ", headers=" + this.headers + ", tracks=" + this.tracks + ')';
        }

        public HulaResult(@JsonProperty("provider") @Nullable String provider, @JsonProperty("host") @Nullable String host, @JsonProperty("type") @Nullable String type, @JsonProperty("quality") int quality, @JsonProperty("url") @NotNull String url, @JsonProperty("headers") @NotNull Map<String, String> map, @JsonProperty("tracks") @NotNull List<HulaTrack> list) {
            this.provider = provider;
            this.host = host;
            this.type = type;
            this.quality = quality;
            this.url = url;
            this.headers = map;
            this.tracks = list;
        }

        public /* synthetic */ HulaResult(String str, String str2, String str3, int i, String str4, Map map, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? 720 : i, (i2 & 16) != 0 ? "" : str4, (i2 & 32) != 0 ? MapsKt.emptyMap() : map, (i2 & 64) != 0 ? CollectionsKt.emptyList() : list);
        }

        @Nullable
        public final String getProvider() {
            return this.provider;
        }

        @Nullable
        public final String getHost() {
            return this.host;
        }

        @Nullable
        public final String getType() {
            return this.type;
        }

        public final int getQuality() {
            return this.quality;
        }

        @NotNull
        public final String getUrl() {
            return this.url;
        }

        @NotNull
        public final Map<String, String> getHeaders() {
            return this.headers;
        }

        @NotNull
        public final List<HulaTrack> getTracks() {
            return this.tracks;
        }
    }

    /* JADX INFO: compiled from: HDO.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/hdo/HDO$HulaTrack;", "", "file", "", "quality", "", "<init>", "(Ljava/lang/String;I)V", "getFile", "()Ljava/lang/String;", "getQuality", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "HDOProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class HulaTrack {

        @JsonProperty("file")
        @NotNull
        private final String file;

        @JsonProperty("quality")
        private final int quality;

        /* JADX WARN: Illegal instructions before constructor call */
        public HulaTrack() {
            String str = null;
            this(str, 0, 3, str);
        }

        public static /* synthetic */ HulaTrack copy$default(HulaTrack hulaTrack, String str, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = hulaTrack.file;
            }
            if ((i2 & 2) != 0) {
                i = hulaTrack.quality;
            }
            return hulaTrack.copy(str, i);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getQuality() {
            return this.quality;
        }

        @NotNull
        public final HulaTrack copy(@JsonProperty("file") @NotNull String file, @JsonProperty("quality") int quality) {
            return new HulaTrack(file, quality);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HulaTrack)) {
                return false;
            }
            HulaTrack hulaTrack = (HulaTrack) other;
            return Intrinsics.areEqual(this.file, hulaTrack.file) && this.quality == hulaTrack.quality;
        }

        public int hashCode() {
            return (this.file.hashCode() * 31) + this.quality;
        }

        @NotNull
        public String toString() {
            return "HulaTrack(file=" + this.file + ", quality=" + this.quality + ')';
        }

        public HulaTrack(@JsonProperty("file") @NotNull String file, @JsonProperty("quality") int quality) {
            this.file = file;
            this.quality = quality;
        }

        public /* synthetic */ HulaTrack(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? 720 : i);
        }

        @NotNull
        public final String getFile() {
            return this.file;
        }

        public final int getQuality() {
            return this.quality;
        }
    }

    private final HulaMovieInfo toLinkData(TmdbLink $this$toLinkData) {
        String strSubstringAfterLast;
        String strSubstringBefore;
        boolean isMovie = $this$toLinkData.getSeason() == null;
        String imdbID = $this$toLinkData.getImdbID();
        Integer tmdbID = $this$toLinkData.getTmdbID();
        String movieName = $this$toLinkData.getMovieName();
        String movieName2 = $this$toLinkData.getMovieName();
        return new HulaMovieInfo(imdbID, tmdbID, movieName, (movieName2 == null || (strSubstringAfterLast = StringsKt.substringAfterLast(movieName2, "(", "")) == null || (strSubstringBefore = StringsKt.substringBefore(strSubstringAfterLast, ")", "")) == null) ? null : StringsKt.toIntOrNull(strSubstringBefore), $this$toLinkData.getSeason(), $this$toLinkData.getEpisode(), isMovie ? "movie" : "tv");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(11:269|173|(1:175)|176|177|227|178|179|235|180|(1:182)(6:183|231|184|185|271|(2:108|253))) */
    /* JADX WARN: Can't wrap try/catch for region: R(15:(1:221)|123|124|274|(3:223|125|(2:127|(1:129)(1:130))(4:273|162|271|(2:108|253)))|132|(1:134)|135|136|216|137|138|233|139|(1:141)(22:142|265|143|144|243|145|146|245|147|148|274|(3:223|125|(0)(0))|132|(0)|135|136|216|137|138|233|139|(0)(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(15:221|123|124|274|(3:223|125|(2:127|(1:129)(1:130))(4:273|162|271|(2:108|253)))|132|(1:134)|135|136|216|137|138|233|139|(1:141)(22:142|265|143|144|243|145|146|245|147|148|274|(3:223|125|(0)(0))|132|(0)|135|136|216|137|138|233|139|(0)(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(22:142|(1:265)|143|144|243|145|146|245|147|148|274|(3:223|125|(0)(0))|132|(0)|135|136|216|137|138|233|139|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(7:110|111|259|112|(3:237|114|(1:116))|121|(15:221|123|124|274|(3:223|125|(2:127|(1:129)(1:130))(4:273|162|271|(2:108|253)))|132|(1:134)|135|136|216|137|138|233|139|(1:141)(22:142|265|143|144|243|145|146|245|147|148|274|(3:223|125|(0)(0))|132|(0)|135|136|216|137|138|233|139|(0)(0)))(5:167|247|168|(1:170)(1:171)|(1:192)(11:269|173|(1:175)|176|177|227|178|179|235|180|(1:182)(6:183|231|184|185|271|(2:108|253))))) */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x057d, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x057f, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0586, code lost:
    
        r4 = r5;
        r5 = r6;
        r1 = r7;
        r6 = r8;
        r7 = r10;
        r14 = r12;
        r13 = r17;
        r16 = r19;
        r17 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0598, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0599, code lost:
    
        r14 = r8;
        r13 = r17;
        r16 = r19;
        r17 = r20;
        r1 = r29;
        r3 = r33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x05ab, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x05ac, code lost:
    
        r14 = r8;
        r16 = r19;
        r17 = r20;
        r1 = r29;
        r3 = r33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x06d7, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x06d8, code lost:
    
        r3 = r32;
        r22 = r18;
        r18 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x06e1, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x06e2, code lost:
    
        r1 = r29;
        r3 = r32;
        r22 = r18;
        r18 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x0703, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x0704, code lost:
    
        r32 = r1;
        r33 = r0;
        r1 = r29;
     */
    /* JADX WARN: Path cross not found for [B:237:0x0446, B:121:0x0466], limit reached: 266 */
    /* JADX WARN: Path cross not found for [B:267:0x03a5, B:95:0x03ba], limit reached: 266 */
    /* JADX WARN: Path cross not found for [B:98:0x03d9, B:102:0x03e2], limit reached: 266 */
    /* JADX WARN: Removed duplicated region for block: B:104:0x03e5 A[Catch: Exception -> 0x0335, TRY_LEAVE, TryCatch #2 {Exception -> 0x0335, blocks: (B:67:0x0316, B:98:0x03d9, B:104:0x03e5, B:94:0x03b4, B:89:0x03a5), top: B:220:0x0314, inners: #4, #28 }] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x03ef A[Catch: Exception -> 0x074e, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x074e, blocks: (B:64:0x030c, B:71:0x033e, B:77:0x0369, B:84:0x0398, B:87:0x039f, B:96:0x03cf, B:106:0x03ef, B:95:0x03ba, B:83:0x038d, B:76:0x035f, B:80:0x0371, B:73:0x0346), top: B:229:0x030c, inners: #15, #26 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0430 A[Catch: Exception -> 0x0746, TRY_LEAVE, TryCatch #21 {Exception -> 0x0746, blocks: (B:108:0x042a, B:110:0x0430), top: B:253:0x042a }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0492 A[Catch: Exception -> 0x05de, TryCatch #6 {Exception -> 0x05de, blocks: (B:125:0x048c, B:127:0x0492, B:132:0x04a9, B:135:0x04b6), top: B:223:0x048c }] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x04b4  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x052f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0530  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x05cb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0316 A[Catch: Exception -> 0x0335, TRY_ENTER, TRY_LEAVE, TryCatch #2 {Exception -> 0x0335, blocks: (B:67:0x0316, B:98:0x03d9, B:104:0x03e5, B:94:0x03b4, B:89:0x03a5), top: B:220:0x0314, inners: #4, #28 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x033e A[Catch: Exception -> 0x074e, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x074e, blocks: (B:64:0x030c, B:71:0x033e, B:77:0x0369, B:84:0x0398, B:87:0x039f, B:96:0x03cf, B:106:0x03ef, B:95:0x03ba, B:83:0x038d, B:76:0x035f, B:80:0x0371, B:73:0x0346), top: B:229:0x030c, inners: #15, #26 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:148:0x056c -> B:223:0x048c). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:162:0x05cb -> B:253:0x042a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:183:0x068e -> B:231:0x069d). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:199:0x0730 -> B:253:0x042a). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object callHulaApiServer(com.hdo.HDO.HulaMovieInfo r32, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r33, kotlin.coroutines.Continuation<? super java.lang.Boolean> r34) {
        /*
            Method dump skipped, instruction units count: 1976
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hdo.HDO.callHulaApiServer(com.hdo.HDO$HulaMovieInfo, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.hdo.HDO$callHulaApiServer$2, reason: invalid class name */
    /* JADX INFO: compiled from: HDO.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.hdo.HDO$callHulaApiServer$2", f = "HDO.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ Map<String, String> $headers;
        final /* synthetic */ int $quality;
        final /* synthetic */ String $referer;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(int i, String str, Map<String, String> map, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$quality = i;
            this.$referer = str;
            this.$headers = map;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = new AnonymousClass2(this.$quality, this.$referer, this.$headers, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
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
                    $this$newExtractorLink.setQuality(this.$quality);
                    $this$newExtractorLink.setReferer(this.$referer);
                    $this$newExtractorLink.setHeaders(this.$headers);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.hdo.HDO$callHulaApiServer$3, reason: invalid class name */
    /* JADX INFO: compiled from: HDO.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.hdo.HDO$callHulaApiServer$3", f = "HDO.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ Map<String, String> $headers;
        final /* synthetic */ String $referer;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(String str, Map<String, String> map, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$referer = str;
            this.$headers = map;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass3 = new AnonymousClass3(this.$referer, this.$headers, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
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
                    $this$newExtractorLink.setQuality(720);
                    $this$newExtractorLink.setReferer(this.$referer);
                    $this$newExtractorLink.setHeaders(this.$headers);
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
