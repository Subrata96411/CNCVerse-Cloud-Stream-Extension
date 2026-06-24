package com.cncverse;

import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.net.URI;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: Extractor.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00130\u00172\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00130\u0017H\u0096@¢\u0006\u0002\u0010\u001bJ\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J2\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00130\u0017H\u0082@¢\u0006\u0002\u0010 R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u0003¨\u0006!"}, d2 = {"Lcom/cncverse/Dailymotion;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "mainUrl", "", "getMainUrl", "()Ljava/lang/String;", "name", "getName", "requiresReferer", "", "getRequiresReferer", "()Z", "baseUrl", "videoIdRegex", "Lkotlin/text/Regex;", "getVideoIdRegex$annotations", "getUrl", "", "url", "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEmbedUrl", "getVideoId", "getStream", "streamLink", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "TamilDhoolProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nExtractor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Extractor.kt\ncom/cncverse/Dailymotion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,75:1\n777#2:76\n873#2,2:77\n1915#2,2:79\n1915#2:81\n1916#2:84\n1915#2,2:85\n1342#3,2:82\n*S KotlinDebug\n*F\n+ 1 Extractor.kt\ncom/cncverse/Dailymotion\n*L\n35#1:76\n35#1:77,2\n37#1:79,2\n42#1:81\n42#1:84\n73#1:85,2\n44#1:82,2\n*E\n"})
public class Dailymotion extends ExtractorApi {
    private final boolean requiresReferer;

    @NotNull
    private final String mainUrl = "https://www.dailymotion.com";

    @NotNull
    private final String name = "Dailymotion";

    @NotNull
    private final String baseUrl = "https://www.dailymotion.com";

    @NotNull
    private final Regex videoIdRegex = new Regex("^[kx][a-zA-Z0-9]+$");

    /* JADX INFO: renamed from: com.cncverse.Dailymotion$getStream$1, reason: invalid class name */
    /* JADX INFO: compiled from: Extractor.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.Dailymotion", f = "Extractor.kt", i = {0, 0, 0}, l = {73}, m = "getStream", n = {"streamLink", "name", "callback"}, nl = {76}, s = {"L$0", "L$1", "L$2"}, v = 2)
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
            return Dailymotion.this.getStream(null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.Dailymotion$getUrl$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Extractor.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.Dailymotion", f = "Extractor.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {29, 38}, m = "getUrl$suspendImpl", n = {"$this", "url", "referer", "subtitleCallback", "callback", "embedUrl", "id", "metaDataUrl", "$this", "url", "referer", "subtitleCallback", "callback", "embedUrl", "id", "metaDataUrl", "response", "qualityUrlRegex", "subtitlesRegex", "urls", "$this$forEach$iv", "element$iv", "videoUrl", "$i$f$forEach", "$i$a$-forEach-Dailymotion$getUrl$2"}, nl = {30, 39}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$14", "L$15", "I$0", "I$1"}, v = 2)
    static final class C00001 extends ContinuationImpl {
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
            return Dailymotion.getUrl$suspendImpl(Dailymotion.this, null, null, null, null, (Continuation) this);
        }
    }

    private static /* synthetic */ void getVideoIdRegex$annotations() {
    }

    @Nullable
    public Object getUrl(@NotNull String str, @Nullable String str2, @NotNull Function1<? super SubtitleFile, Unit> function1, @NotNull Function1<? super ExtractorLink, Unit> function12, @NotNull Continuation<? super Unit> continuation) {
        return getUrl$suspendImpl(this, str, str2, function1, function12, continuation);
    }

    @NotNull
    public String getMainUrl() {
        return this.mainUrl;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public boolean getRequiresReferer() {
        return this.requiresReferer;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x027b -> B:41:0x0294). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ java.lang.Object getUrl$suspendImpl(com.cncverse.Dailymotion r30, java.lang.String r31, java.lang.String r32, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r33, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r34, kotlin.coroutines.Continuation<? super kotlin.Unit> r35) {
        /*
            Method dump skipped, instruction units count: 848
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.Dailymotion.getUrl$suspendImpl(com.cncverse.Dailymotion, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String getUrl$lambda$0(MatchResult it) {
        return (String) it.getGroupValues().get(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String getUrl$lambda$3(MatchResult it) {
        return (String) it.getGroupValues().get(1);
    }

    private final String getEmbedUrl(String url) {
        if (StringsKt.contains$default(url, "/embed/", false, 2, (Object) null) || StringsKt.contains$default(url, "/video/", false, 2, (Object) null)) {
            return url;
        }
        if (!StringsKt.contains$default(url, "geo.dailymotion.com", false, 2, (Object) null)) {
            return null;
        }
        String videoId = StringsKt.substringAfter$default(url, "video=", (String) null, 2, (Object) null);
        return this.baseUrl + "/embed/video/" + videoId;
    }

    private final String getVideoId(String url) {
        String path = new URI(url).getPath();
        String id = StringsKt.substringAfter$default(path, "/video/", (String) null, 2, (Object) null);
        if (this.videoIdRegex.matches(id)) {
            return id;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getStream(java.lang.String r15, java.lang.String r16, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r17, kotlin.coroutines.Continuation<? super kotlin.Unit> r18) {
        /*
            r14 = this;
            r0 = r18
            boolean r1 = r0 instanceof com.cncverse.Dailymotion.AnonymousClass1
            if (r1 == 0) goto L16
            r1 = r0
            com.cncverse.Dailymotion$getStream$1 r1 = (com.cncverse.Dailymotion.AnonymousClass1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r2 = r1.label
            int r2 = r2 - r3
            r1.label = r2
            goto L1b
        L16:
            com.cncverse.Dailymotion$getStream$1 r1 = new com.cncverse.Dailymotion$getStream$1
            r1.<init>(r0)
        L1b:
            r9 = r1
            java.lang.Object r1 = r9.result
            java.lang.Object r12 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r9.label
            switch(r2) {
                case 0: goto L43;
                case 1: goto L31;
                default: goto L27;
            }
        L27:
            r13 = r17
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L31:
            java.lang.Object r2 = r9.L$2
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            java.lang.Object r3 = r9.L$1
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r4 = r9.L$0
            java.lang.String r4 = (java.lang.String) r4
            kotlin.ResultKt.throwOnFailure(r1)
            r13 = r2
            r2 = r1
            goto L70
        L43:
            kotlin.ResultKt.throwOnFailure(r1)
            com.lagradost.cloudstream3.utils.M3u8Helper$Companion r2 = com.lagradost.cloudstream3.utils.M3u8Helper.Companion
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r15)
            r9.L$0 = r3
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r16)
            r9.L$1 = r3
            r13 = r17
            r9.L$2 = r13
            r3 = 1
            r9.label = r3
            java.lang.String r5 = ""
            r6 = 0
            r7 = 0
            r8 = 0
            r10 = 56
            r11 = 0
            r4 = r15
            r3 = r16
            java.lang.Object r2 = com.lagradost.cloudstream3.utils.M3u8Helper.Companion.generateM3u8$default(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            if (r2 != r12) goto L6d
            return r12
        L6d:
            r4 = r15
            r3 = r16
        L70:
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            r5 = 0
            java.util.Iterator r6 = r2.iterator()
        L77:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L85
            java.lang.Object r7 = r6.next()
            r13.invoke(r7)
            goto L77
        L85:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.Dailymotion.getStream(java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
