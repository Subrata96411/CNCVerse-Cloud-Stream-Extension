package com.cncverse.radioindia;

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
import com.lagradost.cloudstream3.LiveSearchResponse;
import com.lagradost.cloudstream3.LiveStreamLoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.settings.Globals;
import java.nio.charset.StandardCharsets;
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
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: RadioIndiaProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nRadioIndiaProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RadioIndiaProvider.kt\ncom/cncverse/radioindia/RadioIndiaProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,460:1\n1642#2,10:461\n1915#2:471\n1916#2:473\n1652#2:474\n1642#2,10:476\n1915#2:486\n1916#2:488\n1652#2:489\n1#3:472\n1#3:475\n1#3:487\n1#3:492\n1342#4,2:490\n*S KotlinDebug\n*F\n+ 1 RadioIndiaProvider.kt\ncom/cncverse/radioindia/RadioIndiaProvider\n*L\n58#1:461,10\n58#1:471\n58#1:473\n58#1:474\n87#1:476,10\n87#1:486\n87#1:488\n87#1:489\n58#1:472\n87#1:487\n434#1:490,2\n*E\n"})
public final class RadioIndiaProvider extends MainAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull

    @Nullable
    private static Context context;
    private final boolean hasDownloadSupport;

    @NotNull
    private String mainUrl = "https://www.radioindia.in";

    @NotNull
    private String name = "Radio India";
    private final boolean hasMainPage = true;

    @NotNull
    private String lang = "ta";

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(TvType.Live);

    @NotNull
    private final List<MainPageData> mainPage = MainAPIKt.mainPageOf(new Pair[]{TuplesKt.to(String.valueOf(getMainUrl()), "Top Stations"), TuplesKt.to(getMainUrl() + "/radio/dance-electronic-160", "Dance / Electronic"), TuplesKt.to(getMainUrl() + "/radio/classical-music-159", "Classical Music"), TuplesKt.to(getMainUrl() + "/radio/oldies-classic-hits-155", "Oldies / Classic Hits"), TuplesKt.to(getMainUrl() + "/radio/jazz-blues-157", "Jazz / Blues"), TuplesKt.to(getMainUrl() + "/radio/metal-153", "Metal"), TuplesKt.to(getMainUrl() + "/radio/rb-hip-hop-163", "R&B / Hip Hop"), TuplesKt.to(getMainUrl() + "/radio/80s-90s-154", "80s / 90s"), TuplesKt.to(getMainUrl() + "/radio/chillout-lounge-161", "Chillout / Lounge"), TuplesKt.to(getMainUrl() + "/radio/rock-156", "Rock"), TuplesKt.to(getMainUrl() + "/radio/pop-todays-hits-152", "Pop / Today's Hits"), TuplesKt.to(getMainUrl() + "/radio/latino-caribbean-162", "Latino / Caribbean"), TuplesKt.to(getMainUrl() + "/radio/country-158", "Country")});

    /* JADX INFO: renamed from: com.cncverse.radioindia.RadioIndiaProvider$getMainPage$1, reason: invalid class name */
    /* JADX INFO: compiled from: RadioIndiaProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.radioindia.RadioIndiaProvider", f = "RadioIndiaProvider.kt", i = {0, 0}, l = {57}, m = "getMainPage", n = {"request", "page"}, nl = {58}, s = {"L$0", "I$0"}, v = 2)
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
            return RadioIndiaProvider.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.radioindia.RadioIndiaProvider$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RadioIndiaProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.radioindia.RadioIndiaProvider", f = "RadioIndiaProvider.kt", i = {0, 1, 1, 1, 1, 1, 1}, l = {94, 109}, m = "load", n = {"url", "url", "document", "html", "title", "poster", "description"}, nl = {95, -1}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"}, v = 2)
    static final class C00001 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C00001(Continuation<? super C00001> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RadioIndiaProvider.this.load(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.radioindia.RadioIndiaProvider$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RadioIndiaProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.radioindia.RadioIndiaProvider", f = "RadioIndiaProvider.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {419, 444}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "isCasting", "data", "subtitleCallback", "callback", "document", "html", "timestampData", "playlistMatch", "itemRegex", "found", "$this$forEach$iv", "element$iv", "m", "cipher", "iv", "type", "decryptedUrl", "isCasting", "$i$f$forEach", "$i$a$-forEach-RadioIndiaProvider$loadLinks$3"}, nl = {420, 443}, s = {"L$0", "L$1", "L$2", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "Z$0", "I$0", "I$1"}, v = 2)
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

        C00011(Continuation<? super C00011> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RadioIndiaProvider.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.radioindia.RadioIndiaProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RadioIndiaProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.radioindia.RadioIndiaProvider", f = "RadioIndiaProvider.kt", i = {0}, l = {86}, m = "search", n = {"query"}, nl = {87}, s = {"L$0"}, v = 2)
    static final class C00021 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00021(Continuation<? super C00021> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RadioIndiaProvider.this.search(null, (Continuation) this);
        }
    }

    /* JADX INFO: compiled from: RadioIndiaProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/cncverse/radioindia/RadioIndiaProvider$Companion;", "", "<init>", "()V", "OMG10", "", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "RadioIndiaProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return RadioIndiaProvider.context;
        }

        public final void setContext(@Nullable Context context) {
            RadioIndiaProvider.context = context;
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

    public boolean getHasDownloadSupport() {
        return this.hasDownloadSupport;
    }

    @NotNull
    public Set<TvType> getSupportedTypes() {
        return this.supportedTypes;
    }

    @NotNull
    public List<MainPageData> getMainPage() {
        return this.mainPage;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getMainPage(int r23, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.MainPageRequest r24, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.HomePageResponse> r25) {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.radioindia.RadioIndiaProvider.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.lagradost.cloudstream3.LiveSearchResponse toSearchResponse(org.jsoup.nodes.Element r11) {
        /*
            r10 = this;
            java.lang.String r0 = "span.mdc-grid-tile__title"
            org.jsoup.nodes.Element r0 = r11.selectFirst(r0)
            r1 = 0
            if (r0 == 0) goto L60
            java.lang.String r0 = r0.text()
            if (r0 != 0) goto L10
            goto L60
        L10:
            r3 = r0
            r0 = r10
            com.lagradost.cloudstream3.MainAPI r0 = (com.lagradost.cloudstream3.MainAPI) r0
            java.lang.String r2 = "href"
            java.lang.String r2 = r11.attr(r2)
            java.lang.String r4 = com.lagradost.cloudstream3.MainAPIKt.fixUrlNull(r0, r2)
            if (r4 != 0) goto L21
            return r1
        L21:
            java.lang.String r0 = "img.mdc-grid-tile__primary-content"
            org.jsoup.nodes.Element r0 = r11.selectFirst(r0)
            if (r0 == 0) goto L43
            java.lang.String r2 = "data-src"
            java.lang.String r2 = r0.attr(r2)
            if (r2 == 0) goto L43
            r5 = r2
            r6 = 0
            r7 = r5
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            boolean r7 = kotlin.text.StringsKt.isBlank(r7)
            if (r7 != 0) goto L3d
            goto L3e
        L3d:
            r2 = r1
        L3e:
            if (r2 != 0) goto L41
            goto L43
        L41:
            r1 = r2
            goto L4b
        L43:
            if (r0 == 0) goto L4b
            java.lang.String r1 = "src"
            java.lang.String r1 = r0.attr(r1)
        L4b:
            r2 = r10
            com.lagradost.cloudstream3.MainAPI r2 = (com.lagradost.cloudstream3.MainAPI) r2
            com.lagradost.cloudstream3.TvType r5 = com.lagradost.cloudstream3.TvType.Live
            com.cncverse.radioindia.RadioIndiaProvider$$ExternalSyntheticLambda7 r7 = new com.cncverse.radioindia.RadioIndiaProvider$$ExternalSyntheticLambda7
            r7.<init>()
            r8 = 8
            r9 = 0
            r6 = 0
            com.lagradost.cloudstream3.LiveSearchResponse r2 = com.lagradost.cloudstream3.MainAPIKt.newLiveSearchResponse$default(r2, r3, r4, r5, r6, r7, r8, r9)
            return r2
        L60:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.radioindia.RadioIndiaProvider.toSearchResponse(org.jsoup.nodes.Element):com.lagradost.cloudstream3.LiveSearchResponse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toSearchResponse$lambda$1(RadioIndiaProvider this$0, String $posterUrl, LiveSearchResponse $this$newLiveSearchResponse) {
        $this$newLiveSearchResponse.setPosterUrl(MainAPIKt.fixUrlNull(this$0, $posterUrl));
        $this$newLiveSearchResponse.setLang("hi");
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object search(@org.jetbrains.annotations.NotNull java.lang.String r23, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.SearchResponse>> r24) {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.radioindia.RadioIndiaProvider.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull java.lang.String r22, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r23) {
        /*
            Method dump skipped, instruction units count: 380
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.radioindia.RadioIndiaProvider.load(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.cncverse.radioindia.RadioIndiaProvider$load$2, reason: invalid class name */
    /* JADX INFO: compiled from: RadioIndiaProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/LiveStreamLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.radioindia.RadioIndiaProvider$load$2", f = "RadioIndiaProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<LiveStreamLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $description;
        final /* synthetic */ String $poster;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, String str2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$poster = str;
            this.$description = str2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = RadioIndiaProvider.this.new AnonymousClass2(this.$poster, this.$description, continuation);
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
                    $this$newLiveStreamLoadResponse.setPosterUrl(MainAPIKt.fixUrlNull(RadioIndiaProvider.this, this.$poster));
                    $this$newLiveStreamLoadResponse.setPlot(this.$description);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    private final String genk(String str) {
        String hex = "";
        int j = 0;
        for (int i = 0; i < 32; i++) {
            int charCode = str.charAt(j);
            StringBuilder sbAppend = new StringBuilder().append(hex);
            String string = Integer.toString(charCode, CharsKt.checkRadix(16));
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            hex = sbAppend.append(string).toString();
            j++;
            if (j >= str.length()) {
                j = 0;
            }
        }
        return hex;
    }

    private final byte[] parseHex(String hexStr) {
        int len = hexStr.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexStr.charAt(i), 16) << 4) + Character.digit(hexStr.charAt(i + 1), 16));
        }
        return data;
    }

    private final String decryptMytuner(String cipherTextBase64, String ivHex, String timestampData) {
        try {
            byte[] cipherData = Base64.decode(cipherTextBase64, 0);
            String keyHex = genk(timestampData);
            byte[] keyBytes = parseHex(keyHex);
            byte[] ivBytes = parseHex(ivHex);
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
            cipher.init(2, secretKey, ivSpec);
            byte[] decryptedBytes = cipher.doFinal(cipherData);
            return StringsKt.trim(StringsKt.trimEnd(new String(decryptedBytes, StandardCharsets.UTF_8), new char[]{0})).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX WARN: Removed duplicated region for block: B:59:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:72:0x030a -> B:73:0x0327). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r32, boolean r33, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r34, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r35, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r36) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 948
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.radioindia.RadioIndiaProvider.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadLinks$lambda$0$0(Context $_ctx) {
        Toast.makeText($_ctx, "⚠️(Opening ads) Subscription expired. If you have renewed your subscription, please re-verify it in Subscription Manager.", 1).show();
    }
}
