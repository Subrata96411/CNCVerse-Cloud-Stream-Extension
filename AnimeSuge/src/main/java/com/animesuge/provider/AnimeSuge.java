package com.animesuge.provider;

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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lagradost.cloudstream3.AnimeLoadResponse;
import com.lagradost.cloudstream3.AnimeSearchResponse;
import com.lagradost.cloudstream3.DubStatus;
import com.lagradost.cloudstream3.Episode;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.settings.Globals;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
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
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Element;

/* JADX INFO: compiled from: AnimeSuge.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nAnimeSuge.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AnimeSuge.kt\ncom/animesuge/provider/AnimeSuge\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 AppUtils.kt\ncom/lagradost/cloudstream3/utils/AppUtils\n+ 6 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n*L\n1#1,549:1\n990#2:550\n1065#2,3:551\n1642#3,10:554\n1915#3:564\n1916#3:566\n1652#3:567\n1696#3,8:568\n1642#3,10:577\n1915#3:587\n1916#3:589\n1652#3:590\n1696#3,8:591\n1586#3:599\n1661#3,3:600\n1915#3:622\n1916#3:624\n1915#3:644\n1915#3:645\n1916#3:647\n1916#3:648\n1915#3:649\n1916#3:670\n1#4:565\n1#4:576\n1#4:588\n1#4:604\n1#4:623\n1#4:626\n1#4:646\n1#4:651\n1#4:669\n63#5:603\n64#5,15:605\n63#5:625\n64#5,15:627\n63#5:650\n64#5,15:652\n50#6:620\n43#6:621\n50#6:642\n43#6:643\n50#6:667\n43#6:668\n*S KotlinDebug\n*F\n+ 1 AnimeSuge.kt\ncom/animesuge/provider/AnimeSuge\n*L\n78#1:550\n78#1:551,3\n110#1:554,10\n110#1:564\n110#1:566\n110#1:567\n111#1:568,8\n132#1:577,10\n132#1:587\n132#1:589\n132#1:590\n132#1:591,8\n159#1:599\n159#1:600,3\n174#1:622\n174#1:624\n254#1:644\n262#1:645\n262#1:647\n254#1:648\n274#1:649\n274#1:670\n110#1:565\n132#1:588\n167#1:604\n248#1:626\n283#1:651\n167#1:603\n167#1:605,15\n248#1:625\n248#1:627,15\n283#1:650\n283#1:652,15\n167#1:620\n167#1:621\n248#1:642\n248#1:643\n283#1:667\n283#1:668\n*E\n"})
public final class AnimeSuge extends MainAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull

    @Nullable
    private static Context context;

    @NotNull
    private String mainUrl = "https://animesuge.cz";

    @NotNull
    private String name = "AnimeSuge";
    private final boolean hasMainPage = true;

    @NotNull
    private String lang = "en";

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(new TvType[]{TvType.Anime, TvType.AnimeMovie, TvType.OVA});

    @NotNull
    private final List<MainPageData> mainPage = MainAPIKt.mainPageOf(new Pair[]{TuplesKt.to(getMainUrl() + "/latest-updated", "Recently Updated"), TuplesKt.to(getMainUrl() + "/new-release", "New Releases"), TuplesKt.to(getMainUrl() + "/most-viewed", "Popular Anime"), TuplesKt.to(getMainUrl() + "/status/finished-airing", "Completed"), TuplesKt.to(getMainUrl() + "/status/currently-airing", "Ongoing")});

    @NotNull
    private final Map<String, String> ajaxHeaders = MapsKt.mapOf(new Pair[]{TuplesKt.to("X-Requested-With", "XMLHttpRequest"), TuplesKt.to("Referer", getMainUrl() + '/')});

    /* JADX INFO: renamed from: com.animesuge.provider.AnimeSuge$getMainPage$1, reason: invalid class name */
    /* JADX INFO: compiled from: AnimeSuge.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.animesuge.provider.AnimeSuge", f = "AnimeSuge.kt", i = {0, 0, 0}, l = {109}, m = "getMainPage", n = {"request", "url", "page"}, nl = {110}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeSuge.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.animesuge.provider.AnimeSuge$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AnimeSuge.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.animesuge.provider.AnimeSuge", f = "AnimeSuge.kt", i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {139, 163, 190}, m = "load", n = {"url", "animeUrl", "url", "animeUrl", "doc", "dataId", "title", "poster", "plot", "genres", "vrf", "url", "animeUrl", "doc", "dataId", "title", "poster", "plot", "genres", "vrf", "epsText", "epsJson", "epsHtml", "epsSoup", "subEpisodes", "dubEpisodes"}, nl = {142, 166, -1}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14"}, v = 2)
    static final class C00001 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
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

        C00001(Continuation<? super C00001> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeSuge.this.load(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.animesuge.provider.AnimeSuge$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AnimeSuge.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.animesuge.provider.AnimeSuge", f = "AnimeSuge.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {241, 276, 287}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "parts", "animeUrl", "dataIds", "selectedType", "isCasting", "data", "subtitleCallback", "callback", "parts", "animeUrl", "dataIds", "selectedType", "serverListText", "serverListJson", "serverListHtml", "serverListSoup", "serversToLoad", "found", "$this$forEach$iv", "element$iv", "serverName", "linkId", "isCasting", "$i$f$forEach", "$i$a$-forEach-AnimeSuge$loadLinks$4", "data", "subtitleCallback", "callback", "parts", "animeUrl", "dataIds", "selectedType", "serverListText", "serverListJson", "serverListHtml", "serverListSoup", "serversToLoad", "found", "$this$forEach$iv", "element$iv", "serverName", "linkId", "serverInfoJson", "serverInfoText", "playerUrl", "isCasting", "$i$f$forEach", "$i$a$-forEach-AnimeSuge$loadLinks$4"}, nl = {247, 282, 288}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$15", "L$16", "L$17", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$15", "L$16", "L$17", "L$18", "L$19", "L$20", "Z$0", "I$0", "I$1"}, v = 2)
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
            return AnimeSuge.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.animesuge.provider.AnimeSuge$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AnimeSuge.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.animesuge.provider.AnimeSuge", f = "AnimeSuge.kt", i = {0, 0}, l = {131}, m = "search", n = {"query", "encoded"}, nl = {132}, s = {"L$0", "L$1"}, v = 2)
    static final class C00021 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00021(Continuation<? super C00021> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeSuge.this.search(null, (Continuation) this);
        }
    }

    /* JADX INFO: compiled from: AnimeSuge.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/animesuge/provider/AnimeSuge$Companion;", "", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "OMG10", "", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "AnimeSuge_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return AnimeSuge.context;
        }

        public final void setContext(@Nullable Context context) {
            AnimeSuge.context = context;
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

    @NotNull
    public List<MainPageData> getMainPage() {
        return this.mainPage;
    }

    private final byte[] rc4(byte[] key, byte[] input) {
        int[] s = new int[256];
        for (int i = 0; i < 256; i++) {
            s[i] = i;
        }
        int j = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            j = (s[i2] + j + (key[i2 % key.length] & 255)) & 255;
            int tmp = s[i2];
            s[i2] = s[j];
            s[j] = tmp;
        }
        int i3 = 0;
        int j2 = 0;
        byte[] out = new byte[input.length];
        int length = input.length;
        for (int x = 0; x < length; x++) {
            i3 = (i3 + 1) & 255;
            j2 = (s[i3] + j2) & 255;
            int tmp2 = s[i3];
            s[i3] = s[j2];
            s[j2] = tmp2;
            out[x] = (byte) ((input[x] & 255) ^ s[(s[i3] + s[j2]) & 255]);
        }
        return out;
    }

    private final byte[] shiftCharcode(String t) {
        byte[] result = new byte[t.length()];
        int length = t.length();
        for (int r = 0; r < length; r++) {
            int s = t.charAt(r);
            switch (r % 8) {
                case 0:
                    s -= 3;
                    break;
                case 1:
                    s += 3;
                    break;
                case 2:
                    s -= 4;
                    break;
                case 3:
                    s += 2;
                    break;
                case 4:
                    s -= 2;
                    break;
                case 5:
                case 7:
                    s += 5;
                    break;
                case 6:
                    s += 4;
                    break;
            }
            result[r] = (byte) s;
        }
        return result;
    }

    private final String rot13(String s) {
        char c;
        String $this$map$iv = s;
        Collection destination$iv$iv = new ArrayList($this$map$iv.length());
        for (int i = 0; i < $this$map$iv.length(); i++) {
            char item$iv$iv = $this$map$iv.charAt(i);
            if ('a' <= item$iv$iv && item$iv$iv < '{') {
                c = (char) ((((item$iv$iv - 'a') + 13) % 26) + 97);
            } else if ('A' <= item$iv$iv && item$iv$iv < '[') {
                c = (char) ((((item$iv$iv - 'A') + 13) % 26) + 65);
            } else {
                c = item$iv$iv;
            }
            destination$iv$iv.add(Character.valueOf(c));
        }
        return CollectionsKt.joinToString$default((List) destination$iv$iv, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    private final String generateVrf(String input) {
        String encoded = StringsKt.replace$default(URLEncoder.encode(input, "UTF-8"), "+", "%20", false, 4, (Object) null);
        byte[] key = "ysJhV6U27FVIjjuk".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(key, "getBytes(...)");
        byte[] bytes = encoded.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] rc4Bytes = rc4(key, bytes);
        String b64 = Base64.encodeToString(rc4Bytes, 10);
        byte[] shifted = shiftCharcode(b64);
        String b64Shifted = Base64.encodeToString(shifted, 10);
        return rot13(b64Shifted);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getMainPage(int r24, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.MainPageRequest r25, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.HomePageResponse> r26) {
        /*
            Method dump skipped, instruction units count: 348
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.animesuge.provider.AnimeSuge.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final SearchResponse toSearchResult(Element $this$toSearchResult) {
        String title;
        Element anchor = $this$toSearchResult.selectFirst("a.poster");
        final String poster = null;
        if (anchor == null && (anchor = $this$toSearchResult.selectFirst("a")) == null) {
            return null;
        }
        String rawHref = anchor.attr("href");
        if (StringsKt.isBlank(rawHref)) {
            rawHref = null;
        }
        if (rawHref == null) {
            return null;
        }
        String href = MainAPIKt.fixUrl(this, new Regex("/ep-\\d+$").replace(rawHref, ""));
        Element it = $this$toSearchResult.selectFirst(".name a, .name, img");
        if (it != null) {
            String strAttr = Intrinsics.areEqual(it.tagName(), "img") ? it.attr("alt") : it.text();
            if (strAttr != null && (title = StringsKt.trim(strAttr).toString()) != null) {
                Element it2 = $this$toSearchResult.selectFirst("img.lazyload, img");
                if (it2 != null) {
                    String it3 = it2.attr("data-src");
                    if (StringsKt.isBlank(it3)) {
                        it3 = null;
                    }
                    if (it3 == null) {
                        it3 = it2.attr("src");
                    }
                    if (it3 != null) {
                        poster = StringsKt.startsWith$default(it3, "http", false, 2, (Object) null) ? it3 : getMainUrl() + '/' + it3;
                    }
                }
                return MainAPIKt.newAnimeSearchResponse$default(this, title, href, (TvType) null, false, new Function1() { // from class: com.animesuge.provider.AnimeSuge$$ExternalSyntheticLambda10
                    public final Object invoke(Object obj) {
                        return AnimeSuge.toSearchResult$lambda$4(poster, (AnimeSearchResponse) obj);
                    }
                }, 12, (Object) null);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toSearchResult$lambda$4(String $poster, AnimeSearchResponse $this$newAnimeSearchResponse) {
        $this$newAnimeSearchResponse.setPosterUrl($poster);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object search(@org.jetbrains.annotations.NotNull java.lang.String r24, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.SearchResponse>> r25) throws java.io.UnsupportedEncodingException {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.animesuge.provider.AnimeSuge.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x037b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x030b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0349 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x035e  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull java.lang.String r36, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r37) {
        /*
            Method dump skipped, instruction units count: 1424
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.animesuge.provider.AnimeSuge.load(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit load$lambda$1$2(int $epNum, String $epTitle, Episode $this$newEpisode) {
        $this$newEpisode.setEpisode(Integer.valueOf($epNum));
        $this$newEpisode.setName($epTitle);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit load$lambda$1$3(int $epNum, String $epTitle, Episode $this$newEpisode) {
        $this$newEpisode.setEpisode(Integer.valueOf($epNum));
        $this$newEpisode.setName($epTitle);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.animesuge.provider.AnimeSuge$load$3, reason: invalid class name */
    /* JADX INFO: compiled from: AnimeSuge.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/AnimeLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.animesuge.provider.AnimeSuge$load$3", f = "AnimeSuge.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<AnimeLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<Episode> $dubEpisodes;
        final /* synthetic */ List<String> $genres;
        final /* synthetic */ String $plot;
        final /* synthetic */ String $poster;
        final /* synthetic */ List<Episode> $subEpisodes;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(String str, String str2, List<String> list, List<Episode> list2, List<Episode> list3, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$poster = str;
            this.$plot = str2;
            this.$genres = list;
            this.$subEpisodes = list2;
            this.$dubEpisodes = list3;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass3 = new AnonymousClass3(this.$poster, this.$plot, this.$genres, this.$subEpisodes, this.$dubEpisodes, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        public final Object invoke(AnimeLoadResponse animeLoadResponse, Continuation<? super Unit> continuation) {
            return create(animeLoadResponse, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            AnimeLoadResponse $this$newAnimeLoadResponse = (AnimeLoadResponse) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newAnimeLoadResponse.setPosterUrl(this.$poster);
                    $this$newAnimeLoadResponse.setPlot(this.$plot);
                    $this$newAnimeLoadResponse.setTags(this.$genres);
                    if (!this.$subEpisodes.isEmpty()) {
                        MainAPIKt.addEpisodes($this$newAnimeLoadResponse, DubStatus.Subbed, this.$subEpisodes);
                    }
                    if (!this.$dubEpisodes.isEmpty()) {
                        MainAPIKt.addEpisodes($this$newAnimeLoadResponse, DubStatus.Dubbed, this.$dubEpisodes);
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:280|172|173|282|174|190|(2:192|(5:214|286|240|127|(2:241|242)(0))(3:194|(1:196)(1:197)|(2:199|(1:201)(8:202|251|203|(3:205|270|206)(1:210)|239|240|127|(0)(0)))))|215|216|240|127|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(19:129|255|130|131|272|132|133|261|134|135|263|136|137|278|138|139|259|140|(1:142)(22:143|249|144|145|245|146|150|(1:152)(8:257|153|154|243|155|156|274|157)|166|(1:168)|266|169|170|(12:280|172|173|282|174|190|(2:192|(5:214|286|240|127|(2:241|242)(0))(3:194|(1:196)(1:197)|(2:199|(1:201)(8:202|251|203|(3:205|270|206)(1:210)|239|240|127|(0)(0)))))|215|216|240|127|(0)(0))(1:188)|189|190|(0)|215|216|240|127|(0)(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(22:143|(1:249)|144|145|245|146|150|(1:152)(8:257|153|154|243|155|156|274|157)|166|(1:168)|266|169|170|(12:280|172|173|282|174|190|(2:192|(5:214|286|240|127|(2:241|242)(0))(3:194|(1:196)(1:197)|(2:199|(1:201)(8:202|251|203|(3:205|270|206)(1:210)|239|240|127|(0)(0)))))|215|216|240|127|(0)(0))(1:188)|189|190|(0)|215|216|240|127|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(22:143|249|144|145|245|146|150|(1:152)(8:257|153|154|243|155|156|274|157)|166|(1:168)|266|169|170|(12:280|172|173|282|174|190|(2:192|(5:214|286|240|127|(2:241|242)(0))(3:194|(1:196)(1:197)|(2:199|(1:201)(8:202|251|203|(3:205|270|206)(1:210)|239|240|127|(0)(0)))))|215|216|240|127|(0)(0))(1:188)|189|190|(0)|215|216|240|127|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(8:(1:257)|153|154|243|155|156|274|157) */
    /* JADX WARN: Can't wrap try/catch for region: R(8:257|153|154|243|155|156|274|157) */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0680, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0681, code lost:
    
        r18 = kotlin.Result.Companion;
        r0 = kotlin.Result.constructor-impl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x06be, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x06c0, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x06c1, code lost:
    
        r53 = r2;
        r54 = r0;
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x06d1, code lost:
    
        r1 = kotlin.Result.Companion;
        r0 = kotlin.Result.constructor-impl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x06f8, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x0701, code lost:
    
        com.lagradost.cloudstream3.mvvm.ArchComponentExtKt.logError((java.lang.Throwable) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x0709, code lost:
    
        r2 = r53;
        r33 = r8;
        r35 = r10;
        r36 = r11;
        r8 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:218:0x081d, code lost:
    
        r2 = r53;
        r33 = r8;
        r35 = r10;
        r36 = r11;
        r8 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:219:0x082a, code lost:
    
        r23 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x0841, code lost:
    
        r2 = r43;
        r5 = r50;
        r3 = r16;
        r8 = r23;
        r14 = r36;
        r12 = r38;
        r35 = r38;
        r36 = r39;
        r33 = r36;
        r7 = r16;
        r23 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:225:0x0862, code lost:
    
        r16 = r1;
        r1 = r50;
        r50 = r16;
        r38 = r2;
        r36 = r4;
        r46 = r6;
        r41 = r8;
        r39 = r9;
        r42 = false;
        r43 = r12;
        r45 = r14;
        r47 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x0880, code lost:
    
        r16 = r1;
        r1 = r50;
        r50 = r16;
        r38 = r2;
        r36 = r4;
        r47 = r5;
        r46 = r6;
        r41 = r8;
        r39 = r9;
        r42 = false;
        r43 = r12;
        r45 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x089c, code lost:
    
        r48 = r5;
        r42 = r42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x08a1, code lost:
    
        r16 = r1;
        r1 = r50;
        r50 = r16;
        r36 = r4;
        r46 = r6;
        r41 = r8;
        r39 = r9;
        r42 = false;
        r43 = r12;
        r45 = r14;
        r47 = r38;
        r48 = r5;
        r38 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x08c0, code lost:
    
        r40 = r10;
        r42 = r42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:233:0x08c4, code lost:
    
        r16 = r1;
        r1 = r50;
        r50 = r16;
        r36 = r4;
        r48 = r5;
        r46 = r6;
        r41 = r8;
        r39 = r9;
        r40 = r10;
        r42 = false;
        r43 = r12;
        r45 = r14;
        r47 = r38;
        r38 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x08e5, code lost:
    
        r2 = r50;
        r5 = r1;
        r42 = r42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:237:0x090a, code lost:
    
        r8 = r23;
        r14 = r36;
        r12 = r38;
        r35 = r39;
        r36 = r40;
        r33 = r41;
        r23 = r46;
     */
    /* JADX WARN: Not initialized variable reg: 25, insn: 0x0164: MOVE (r7 I:??[OBJECT, ARRAY]) = (r25 I:??[OBJECT, ARRAY] A[D('found' kotlin.jvm.internal.Ref$BooleanRef)]), block:B:19:0x015e */
    /* JADX WARN: Not initialized variable reg: 29, insn: 0x0166: MOVE (r21 I:??[OBJECT, ARRAY]) = (r29 I:??[OBJECT, ARRAY] A[D('serverListJson' com.animesuge.provider.AnimeSuge$AjaxResponse)]), block:B:19:0x015e */
    /* JADX WARN: Not initialized variable reg: 31, insn: 0x0168: MOVE (r8 I:??[OBJECT, ARRAY]) = (r31 I:??[OBJECT, ARRAY] A[D('selectedType' java.lang.String)]), block:B:19:0x015e */
    /* JADX WARN: Path cross not found for [B:192:0x073f, B:215:0x0814], limit reached: 274 */
    /* JADX WARN: Path cross not found for [B:215:0x0814, B:192:0x073f], limit reached: 274 */
    /* JADX WARN: Path cross not found for [B:284:0x039e, B:87:0x03b3], limit reached: 274 */
    /* JADX WARN: Path cross not found for [B:87:0x03b3, B:284:0x039e], limit reached: 274 */
    /* JADX WARN: Removed duplicated region for block: B:129:0x051b  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0691  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x06e1  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0718  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x073f A[Catch: Exception -> 0x081c, TryCatch #16 {Exception -> 0x081c, blocks: (B:166:0x06db, B:169:0x06e2, B:190:0x0735, B:192:0x073f, B:194:0x0746, B:199:0x0756, B:189:0x071a, B:165:0x06d1), top: B:266:0x06e2 }] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0803  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x080a  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0937  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x035d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:257:0x069a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:280:0x06e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:284:0x039e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0397  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x03d3  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x03d8  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:202:0x07e7 -> B:251:0x07fb). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:238:0x0920 -> B:239:0x0921). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r50, boolean r51, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r52, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r53, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r54) {
        /*
            Method dump skipped, instruction units count: 2380
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.animesuge.provider.AnimeSuge.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadLinks$lambda$0$0(Context $_ctx) {
        Toast.makeText($_ctx, "⚠️(Opening ads) Subscription expired. If you have renewed your subscription, please re-verify it in Subscription Manager.", 1).show();
    }

    /* JADX INFO: compiled from: AnimeSuge.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J&\u0010\u000f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/animesuge/provider/AnimeSuge$AjaxResponse;", "", "status", "", "result", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;)V", "getStatus", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getResult", "()Ljava/lang/String;", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/lang/String;)Lcom/animesuge/provider/AnimeSuge$AjaxResponse;", "equals", "", "other", "hashCode", "toString", "AnimeSuge_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class AjaxResponse {

        @JsonProperty("result")
        @Nullable
        private final String result;

        @JsonProperty("status")
        @Nullable
        private final Integer status;

        /* JADX WARN: Multi-variable type inference failed */
        public AjaxResponse() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ AjaxResponse copy$default(AjaxResponse ajaxResponse, Integer num, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                num = ajaxResponse.status;
            }
            if ((i & 2) != 0) {
                str = ajaxResponse.result;
            }
            return ajaxResponse.copy(num, str);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getStatus() {
            return this.status;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getResult() {
            return this.result;
        }

        @NotNull
        public final AjaxResponse copy(@JsonProperty("status") @Nullable Integer status, @JsonProperty("result") @Nullable String result) {
            return new AjaxResponse(status, result);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AjaxResponse)) {
                return false;
            }
            AjaxResponse ajaxResponse = (AjaxResponse) other;
            return Intrinsics.areEqual(this.status, ajaxResponse.status) && Intrinsics.areEqual(this.result, ajaxResponse.result);
        }

        public int hashCode() {
            return ((this.status == null ? 0 : this.status.hashCode()) * 31) + (this.result != null ? this.result.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "AjaxResponse(status=" + this.status + ", result=" + this.result + ')';
        }

        public AjaxResponse(@JsonProperty("status") @Nullable Integer status, @JsonProperty("result") @Nullable String result) {
            this.status = status;
            this.result = result;
        }

        public /* synthetic */ AjaxResponse(Integer num, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str);
        }

        @Nullable
        public final Integer getStatus() {
            return this.status;
        }

        @Nullable
        public final String getResult() {
            return this.result;
        }
    }

    /* JADX INFO: compiled from: AnimeSuge.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J&\u0010\u000f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/animesuge/provider/AnimeSuge$ServerInfoResponse;", "", "status", "", "result", "Lcom/animesuge/provider/AnimeSuge$ServerInfoResult;", "<init>", "(Ljava/lang/Integer;Lcom/animesuge/provider/AnimeSuge$ServerInfoResult;)V", "getStatus", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getResult", "()Lcom/animesuge/provider/AnimeSuge$ServerInfoResult;", "component1", "component2", "copy", "(Ljava/lang/Integer;Lcom/animesuge/provider/AnimeSuge$ServerInfoResult;)Lcom/animesuge/provider/AnimeSuge$ServerInfoResponse;", "equals", "", "other", "hashCode", "toString", "", "AnimeSuge_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ServerInfoResponse {

        @JsonProperty("result")
        @Nullable
        private final ServerInfoResult result;

        @JsonProperty("status")
        @Nullable
        private final Integer status;

        /* JADX WARN: Multi-variable type inference failed */
        public ServerInfoResponse() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ ServerInfoResponse copy$default(ServerInfoResponse serverInfoResponse, Integer num, ServerInfoResult serverInfoResult, int i, Object obj) {
            if ((i & 1) != 0) {
                num = serverInfoResponse.status;
            }
            if ((i & 2) != 0) {
                serverInfoResult = serverInfoResponse.result;
            }
            return serverInfoResponse.copy(num, serverInfoResult);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getStatus() {
            return this.status;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ServerInfoResult getResult() {
            return this.result;
        }

        @NotNull
        public final ServerInfoResponse copy(@JsonProperty("status") @Nullable Integer status, @JsonProperty("result") @Nullable ServerInfoResult result) {
            return new ServerInfoResponse(status, result);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ServerInfoResponse)) {
                return false;
            }
            ServerInfoResponse serverInfoResponse = (ServerInfoResponse) other;
            return Intrinsics.areEqual(this.status, serverInfoResponse.status) && Intrinsics.areEqual(this.result, serverInfoResponse.result);
        }

        public int hashCode() {
            return ((this.status == null ? 0 : this.status.hashCode()) * 31) + (this.result != null ? this.result.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "ServerInfoResponse(status=" + this.status + ", result=" + this.result + ')';
        }

        public ServerInfoResponse(@JsonProperty("status") @Nullable Integer status, @JsonProperty("result") @Nullable ServerInfoResult result) {
            this.status = status;
            this.result = result;
        }

        public /* synthetic */ ServerInfoResponse(Integer num, ServerInfoResult serverInfoResult, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : serverInfoResult);
        }

        @Nullable
        public final Integer getStatus() {
            return this.status;
        }

        @Nullable
        public final ServerInfoResult getResult() {
            return this.result;
        }
    }

    /* JADX INFO: compiled from: AnimeSuge.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/animesuge/provider/AnimeSuge$ServerInfoResult;", "", "url", "", "<init>", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "AnimeSuge_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ServerInfoResult {

        @JsonProperty("url")
        @Nullable
        private final String url;

        /* JADX WARN: Illegal instructions before constructor call */
        public ServerInfoResult() {
            String str = null;
            this(str, 1, str);
        }

        public static /* synthetic */ ServerInfoResult copy$default(ServerInfoResult serverInfoResult, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = serverInfoResult.url;
            }
            return serverInfoResult.copy(str);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @NotNull
        public final ServerInfoResult copy(@JsonProperty("url") @Nullable String url) {
            return new ServerInfoResult(url);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ServerInfoResult) && Intrinsics.areEqual(this.url, ((ServerInfoResult) other).url);
        }

        public int hashCode() {
            if (this.url == null) {
                return 0;
            }
            return this.url.hashCode();
        }

        @NotNull
        public String toString() {
            return "ServerInfoResult(url=" + this.url + ')';
        }

        public ServerInfoResult(@JsonProperty("url") @Nullable String url) {
            this.url = url;
        }

        public /* synthetic */ ServerInfoResult(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str);
        }

        @Nullable
        public final String getUrl() {
            return this.url;
        }
    }


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */
}
