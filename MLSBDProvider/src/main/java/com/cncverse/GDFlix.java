package com.cncverse;

import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.ParCollectionsKt;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Element;

/* JADX INFO: compiled from: Extractors.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000e\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010\u000fJH\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00110\u00152\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00110\u0015H\u0096@¢\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/cncverse/GDFlix;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getLatestUrl", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUrl", "", "url", "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBaseUrl", "MLSBDProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nExtractors.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Extractors.kt\ncom/cncverse/GDFlix\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,606:1\n1#2:607\n*E\n"})
public final class GDFlix extends ExtractorApi {
    private final boolean requiresReferer;

    @NotNull
    private final String name = "GDFlix";

    @NotNull
    private final String mainUrl = "https://*.gdflix.*";

    /* JADX INFO: renamed from: com.cncverse.GDFlix$getLatestUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Extractors.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.GDFlix", f = "Extractors.kt", i = {}, l = {378}, m = "getLatestUrl", n = {}, nl = {377}, s = {}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return GDFlix.this.getLatestUrl((Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.GDFlix$getUrl$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Extractors.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.GDFlix", f = "Extractors.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {392, 394, 401}, m = "getUrl", n = {"url", "referer", "subtitleCallback", "callback", "url", "referer", "subtitleCallback", "callback", "latestUrl", "newUrl", "url", "referer", "subtitleCallback", "callback", "latestUrl", "newUrl", "document", "fileName", "fileSize", "quality"}, nl = {393, 395, 527}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0"}, v = 2)
    static final class C00021 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        /* synthetic */ Object result;

        C00021(Continuation<? super C00021> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return GDFlix.this.getUrl(null, null, null, null, (Continuation) this);
        }
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public String getMainUrl() {
        return this.mainUrl;
    }

    public boolean getRequiresReferer() {
        return this.requiresReferer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getLatestUrl(kotlin.coroutines.Continuation<? super java.lang.String> r23) {
        /*
            r22 = this;
            r0 = r23
            boolean r1 = r0 instanceof com.cncverse.GDFlix.AnonymousClass1
            if (r1 == 0) goto L18
            r1 = r0
            com.cncverse.GDFlix$getLatestUrl$1 r1 = (com.cncverse.GDFlix.AnonymousClass1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L18
            int r2 = r1.label
            int r2 = r2 - r3
            r1.label = r2
            r2 = r22
            goto L1f
        L18:
            com.cncverse.GDFlix$getLatestUrl$1 r1 = new com.cncverse.GDFlix$getLatestUrl$1
            r2 = r22
            r1.<init>(r0)
        L1f:
            java.lang.Object r3 = r1.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r1.label
            r6 = 1
            switch(r5) {
                case 0: goto L3e;
                case 1: goto L35;
                default: goto L2b;
            }
        L2b:
            r17 = r1
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L35:
            kotlin.ResultKt.throwOnFailure(r3)
            r17 = r1
            r1 = r3
            r21 = 1
            goto L7f
        L3e:
            kotlin.ResultKt.throwOnFailure(r3)
            r5 = r3
            com.lagradost.nicehttp.Requests r3 = com.lagradost.cloudstream3.MainActivityKt.getApp()
            r1.label = r6
            r7 = r4
            java.lang.String r4 = "https://raw.githubusercontent.com/SaurabhKaperwan/Utils/refs/heads/main/urls.json"
            r8 = r5
            r5 = 0
            r9 = 1
            r6 = 0
            r10 = r7
            r7 = 0
            r11 = r8
            r8 = 0
            r12 = 1
            r9 = 0
            r13 = r10
            r10 = 0
            r14 = r11
            r11 = 0
            r15 = r13
            r16 = 1
            r12 = 0
            r17 = r14
            r14 = 0
            r18 = r15
            r15 = 0
            r19 = 1
            r16 = 0
            r20 = r18
            r18 = 4094(0xffe, float:5.737E-42)
            r21 = 1
            r19 = 0
            r0 = r17
            r17 = r1
            r1 = r0
            r0 = r20
            java.lang.Object r3 = com.lagradost.nicehttp.Requests.get$default(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r14, r15, r16, r17, r18, r19)
            if (r3 != r0) goto L7f
            return r0
        L7f:
            com.lagradost.nicehttp.NiceResponse r3 = (com.lagradost.nicehttp.NiceResponse) r3
            java.lang.String r0 = r3.getText()
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>(r0)
            java.lang.String r0 = "gdflix"
            java.lang.String r0 = r3.optString(r0)
            r3 = r0
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            if (r3 == 0) goto L9f
            int r3 = r3.length()
            if (r3 != 0) goto L9d
            goto L9f
        L9d:
            r6 = 0
            goto La0
        L9f:
            r6 = 1
        La0:
            if (r6 == 0) goto La7
            java.lang.String r3 = r2.getMainUrl()
            return r3
        La7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.GDFlix.getLatestUrl(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x010c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x019d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getUrl(@org.jetbrains.annotations.NotNull java.lang.String r29, @org.jetbrains.annotations.Nullable java.lang.String r30, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r31, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r32, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r33) {
        /*
            Method dump skipped, instruction units count: 436
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.GDFlix.getUrl(java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.cncverse.GDFlix$getUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: Extractors.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u000b\u0010\u0002\u001a\u00070\u0003¢\u0006\u0002\b\u0004H\n"}, d2 = {"<anonymous>", "", "anchor", "Lorg/jsoup/nodes/Element;", "Lkotlin/jvm/internal/EnhancedNullability;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.GDFlix$getUrl$2", f = "Extractors.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 7, 7, 7}, l = {408, 417, 429, 439, 440, 464, 510, 511}, m = "invokeSuspend", n = {"anchor", "text", "link", "anchor", "text", "link", "link", "anchor", "text", "link", "baseUrlLink", "finalURL", "anchor", "text", "link", "anchor", "text", "link", "anchor", "text", "link", "driveLink", "id", "doId", "baseUrls", "anchor", "text", "link", "anchor", "text", "link"}, nl = {407, 416, 428, 440, 452, 503, 511, 517}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<Element, Continuation<? super Object>, Object> {
        final /* synthetic */ Function1<ExtractorLink, Unit> $callback;
        final /* synthetic */ String $fileName;
        final /* synthetic */ String $fileSize;
        final /* synthetic */ String $latestUrl;
        final /* synthetic */ int $quality;
        final /* synthetic */ Function1<SubtitleFile, Unit> $subtitleCallback;
        /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        final /* synthetic */ GDFlix this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Function1<? super ExtractorLink, Unit> function1, String str, String str2, GDFlix gDFlix, String str3, int i, Function1<? super SubtitleFile, Unit> function12, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$callback = function1;
            this.$fileName = str;
            this.$fileSize = str2;
            this.this$0 = gDFlix;
            this.$latestUrl = str3;
            this.$quality = i;
            this.$subtitleCallback = function12;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = new AnonymousClass2(this.$callback, this.$fileName, this.$fileSize, this.this$0, this.$latestUrl, this.$quality, this.$subtitleCallback, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(Element element, Continuation<Object> continuation) {
            return create(element, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:57:0x029a A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:58:0x029b  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x03ce A[RETURN] */
        /* JADX WARN: Type inference failed for: r3v0, types: [int] */
        /* JADX WARN: Type inference failed for: r3v1 */
        /* JADX WARN: Type inference failed for: r3v10 */
        /* JADX WARN: Type inference failed for: r3v11 */
        /* JADX WARN: Type inference failed for: r3v12 */
        /* JADX WARN: Type inference failed for: r3v25, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v28 */
        /* JADX WARN: Type inference failed for: r3v3 */
        /* JADX WARN: Type inference failed for: r3v38, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v41 */
        /* JADX WARN: Type inference failed for: r3v45 */
        /* JADX WARN: Type inference failed for: r3v46 */
        /* JADX WARN: Type inference failed for: r3v47 */
        /* JADX WARN: Type inference failed for: r3v48 */
        /* JADX WARN: Type inference failed for: r3v49 */
        /* JADX WARN: Type inference failed for: r3v50 */
        /* JADX WARN: Type inference failed for: r3v9 */
        /* JADX WARN: Type inference failed for: r8v0 */
        /* JADX WARN: Type inference failed for: r8v1 */
        /* JADX WARN: Type inference failed for: r8v11 */
        /* JADX WARN: Type inference failed for: r8v19 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r29) throws java.io.UnsupportedEncodingException {
            /*
                Method dump skipped, instruction units count: 1208
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.cncverse.GDFlix.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: com.cncverse.GDFlix$getUrl$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: Extractors.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.cncverse.GDFlix$getUrl$2$1", f = "Extractors.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $quality;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(int i, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$quality = i;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                Continuation<Unit> anonymousClass1 = new AnonymousClass1(this.$quality, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
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
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        /* JADX INFO: renamed from: com.cncverse.GDFlix$getUrl$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Extractors.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.cncverse.GDFlix$getUrl$2$2", f = "Extractors.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class C00002 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $quality;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00002(int i, Continuation<? super C00002> continuation) {
                super(2, continuation);
                this.$quality = i;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                Continuation<Unit> c00002 = new C00002(this.$quality, continuation);
                c00002.L$0 = obj;
                return c00002;
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
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        /* JADX INFO: renamed from: com.cncverse.GDFlix$getUrl$2$3, reason: invalid class name */
        /* JADX INFO: compiled from: Extractors.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.cncverse.GDFlix$getUrl$2$3", f = "Extractors.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class AnonymousClass3 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
            final /* synthetic */ int $quality;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(int i, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.$quality = i;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                Continuation<Unit> anonymousClass3 = new AnonymousClass3(this.$quality, continuation);
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
                        $this$newExtractorLink.setQuality(this.$quality);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        /* JADX INFO: renamed from: com.cncverse.GDFlix$getUrl$2$4, reason: invalid class name */
        /* JADX INFO: compiled from: Extractors.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u000b\u0010\u0003\u001a\u00070\u0004¢\u0006\u0002\b\u0005H\n"}, d2 = {"<anonymous>", "", "", "btn", "Lorg/jsoup/nodes/Element;", "Lkotlin/jvm/internal/EnhancedNullability;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.cncverse.GDFlix$getUrl$2$4", f = "Extractors.kt", i = {0, 0, 1, 1}, l = {442, 443}, m = "invokeSuspend", n = {"btn", "serverUrl", "btn", "serverUrl"}, nl = {443, 450}, s = {"L$0", "L$1", "L$0", "L$1"}, v = 2)
        static final class AnonymousClass4 extends SuspendLambda implements Function2<Element, Continuation<? super List<? extends Unit>>, Object> {
            final /* synthetic */ Function1<ExtractorLink, Unit> $callback;
            final /* synthetic */ String $fileName;
            final /* synthetic */ String $fileSize;
            final /* synthetic */ String $latestUrl;
            final /* synthetic */ int $quality;
            /* synthetic */ Object L$0;
            Object L$1;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass4(String str, Function1<? super ExtractorLink, Unit> function1, String str2, String str3, int i, Continuation<? super AnonymousClass4> continuation) {
                super(2, continuation);
                this.$latestUrl = str;
                this.$callback = function1;
                this.$fileName = str2;
                this.$fileSize = str3;
                this.$quality = i;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                Continuation<Unit> anonymousClass4 = new AnonymousClass4(this.$latestUrl, this.$callback, this.$fileName, this.$fileSize, this.$quality, continuation);
                anonymousClass4.L$0 = obj;
                return anonymousClass4;
            }

            public final Object invoke(Element element, Continuation<? super List<Unit>> continuation) {
                return create(element, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object $result) {
                Object obj;
                String serverUrl;
                Element btn = (Element) this.L$0;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        String serverUrl2 = this.$latestUrl + btn.attr("href");
                        this.L$0 = SpillingKt.nullOutSpilledVariable(btn);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(serverUrl2);
                        this.label = 1;
                        Object obj2 = Requests.get$default(MainActivityKt.getApp(), serverUrl2, (Map) null, (String) null, (Map) null, (Map) null, false, 0, (TimeUnit) null, 0L, (Interceptor) null, false, (ResponseParser) null, (Continuation) this, 4094, (Object) null);
                        if (obj2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj = obj2;
                        serverUrl = serverUrl2;
                        break;
                        break;
                    case 1:
                        serverUrl = (String) this.L$1;
                        ResultKt.throwOnFailure($result);
                        obj = $result;
                        break;
                    case 2:
                        ResultKt.throwOnFailure($result);
                        return $result;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                this.L$0 = SpillingKt.nullOutSpilledVariable(btn);
                this.L$1 = SpillingKt.nullOutSpilledVariable(serverUrl);
                this.label = 2;
                Object objAmap = ParCollectionsKt.amap(((NiceResponse) obj).getDocument().select("div.mb-4 > a"), new AnonymousClass1(this.$callback, this.$fileName, this.$fileSize, this.$quality, null), (Continuation) this);
                if (objAmap == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return objAmap;
            }

            /* JADX INFO: renamed from: com.cncverse.GDFlix$getUrl$2$4$1, reason: invalid class name */
            /* JADX INFO: compiled from: Extractors.kt */
            @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u000b\u0010\u0002\u001a\u00070\u0003¢\u0006\u0002\b\u0004H\n"}, d2 = {"<anonymous>", "", "sourceAnchor", "Lorg/jsoup/nodes/Element;", "Lkotlin/jvm/internal/EnhancedNullability;"}, k = 3, mv = {2, 3, 0}, xi = 48)
            @DebugMetadata(c = "com.cncverse.GDFlix$getUrl$2$4$1", f = "Extractors.kt", i = {0, 0}, l = {446}, m = "invokeSuspend", n = {"sourceAnchor", "source"}, nl = {445}, s = {"L$0", "L$1"}, v = 2)
            static final class AnonymousClass1 extends SuspendLambda implements Function2<Element, Continuation<? super Unit>, Object> {
                final /* synthetic */ Function1<ExtractorLink, Unit> $callback;
                final /* synthetic */ String $fileName;
                final /* synthetic */ String $fileSize;
                final /* synthetic */ int $quality;
                /* synthetic */ Object L$0;
                Object L$1;
                Object L$2;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(Function1<? super ExtractorLink, Unit> function1, String str, String str2, int i, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$callback = function1;
                    this.$fileName = str;
                    this.$fileSize = str2;
                    this.$quality = i;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    Continuation<Unit> anonymousClass1 = new AnonymousClass1(this.$callback, this.$fileName, this.$fileSize, this.$quality, continuation);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
                }

                public final Object invoke(Element element, Continuation<? super Unit> continuation) {
                    return create(element, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object $result) {
                    Object objNewExtractorLink$default;
                    Function1<ExtractorLink, Unit> function1;
                    Element sourceAnchor = (Element) this.L$0;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            String source = sourceAnchor.attr("href");
                            Function1<ExtractorLink, Unit> function12 = this.$callback;
                            this.L$0 = SpillingKt.nullOutSpilledVariable(sourceAnchor);
                            this.L$1 = SpillingKt.nullOutSpilledVariable(source);
                            this.L$2 = function12;
                            this.label = 1;
                            objNewExtractorLink$default = ExtractorApiKt.newExtractorLink$default("GDFlix[Index]", "GDFlix[Index] " + this.$fileName + '[' + this.$fileSize + ']', source, (ExtractorLinkType) null, new C00011(this.$quality, null), (Continuation) this, 8, (Object) null);
                            if (objNewExtractorLink$default == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            function1 = function12;
                            break;
                            break;
                        case 1:
                            function1 = (Function1) this.L$2;
                            ResultKt.throwOnFailure($result);
                            objNewExtractorLink$default = $result;
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    function1.invoke(objNewExtractorLink$default);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: com.cncverse.GDFlix$getUrl$2$4$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: Extractors.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
                @DebugMetadata(c = "com.cncverse.GDFlix$getUrl$2$4$1$1", f = "Extractors.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
                static final class C00011 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
                    final /* synthetic */ int $quality;
                    private /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00011(int i, Continuation<? super C00011> continuation) {
                        super(2, continuation);
                        this.$quality = i;
                    }

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        Continuation<Unit> c00011 = new C00011(this.$quality, continuation);
                        c00011.L$0 = obj;
                        return c00011;
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
                                return Unit.INSTANCE;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }
                }
            }
        }

        /* JADX INFO: renamed from: com.cncverse.GDFlix$getUrl$2$5, reason: invalid class name */
        /* JADX INFO: compiled from: Extractors.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "baseUrl", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.cncverse.GDFlix$getUrl$2$5", f = "Extractors.kt", i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {466, 485, 496}, m = "invokeSuspend", n = {"baseUrl", "indexbotLink", "baseUrl", "indexbotLink", "indexbotResponse", "cookiesSSID", "indexbotDoc", "token", "postId", "requestBody", "headers", "cookies", "baseUrl", "indexbotLink", "indexbotResponse", "cookiesSSID", "indexbotDoc", "token", "postId", "requestBody", "headers", "cookies", "downloadLink"}, nl = {468, 491, 495}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10"}, v = 2)
        static final class AnonymousClass5 extends SuspendLambda implements Function2<String, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<ExtractorLink, Unit> $callback;
            final /* synthetic */ String $doId;
            final /* synthetic */ String $fileName;
            final /* synthetic */ String $fileSize;
            final /* synthetic */ String $id;
            final /* synthetic */ int $quality;
            /* synthetic */ Object L$0;
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

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass5(String str, String str2, Function1<? super ExtractorLink, Unit> function1, String str3, String str4, int i, Continuation<? super AnonymousClass5> continuation) {
                super(2, continuation);
                this.$id = str;
                this.$doId = str2;
                this.$callback = function1;
                this.$fileName = str3;
                this.$fileSize = str4;
                this.$quality = i;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                Continuation<Unit> anonymousClass5 = new AnonymousClass5(this.$id, this.$doId, this.$callback, this.$fileName, this.$fileSize, this.$quality, continuation);
                anonymousClass5.L$0 = obj;
                return anonymousClass5;
            }

            public final Object invoke(String str, Continuation<? super Unit> continuation) {
                return create(str, continuation).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x00f2  */
            /* JADX WARN: Removed duplicated region for block: B:42:0x024c  */
            /* JADX WARN: Removed duplicated region for block: B:44:0x024f  */
            /* JADX WARN: Removed duplicated region for block: B:47:0x02e2 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:48:0x02e3  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r40) {
                /*
                    Method dump skipped, instruction units count: 768
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.cncverse.GDFlix.AnonymousClass2.AnonymousClass5.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            /* JADX INFO: renamed from: com.cncverse.GDFlix$getUrl$2$5$1, reason: invalid class name */
            /* JADX INFO: compiled from: Extractors.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
            @DebugMetadata(c = "com.cncverse.GDFlix$getUrl$2$5$1", f = "Extractors.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
            static final class AnonymousClass1 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $baseUrl;
                final /* synthetic */ int $quality;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(String str, int i, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$baseUrl = str;
                    this.$quality = i;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    Continuation<Unit> anonymousClass1 = new AnonymousClass1(this.$baseUrl, this.$quality, continuation);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
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
                            $this$newExtractorLink.setReferer(this.$baseUrl);
                            $this$newExtractorLink.setQuality(this.$quality);
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }
        }

        /* JADX INFO: renamed from: com.cncverse.GDFlix$getUrl$2$6, reason: invalid class name */
        /* JADX INFO: compiled from: Extractors.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u000b\u0010\u0002\u001a\u00070\u0003¢\u0006\u0002\b\u0004H\n"}, d2 = {"<anonymous>", "", "gofileAnchor", "Lorg/jsoup/nodes/Element;", "Lkotlin/jvm/internal/EnhancedNullability;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.cncverse.GDFlix$getUrl$2$6", f = "Extractors.kt", i = {0, 0}, l = {514}, m = "invokeSuspend", n = {"gofileAnchor", "link"}, nl = {516}, s = {"L$0", "L$1"}, v = 2)
        static final class AnonymousClass6 extends SuspendLambda implements Function2<Element, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<ExtractorLink, Unit> $callback;
            final /* synthetic */ Function1<SubtitleFile, Unit> $subtitleCallback;
            /* synthetic */ Object L$0;
            Object L$1;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass6(Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function12, Continuation<? super AnonymousClass6> continuation) {
                super(2, continuation);
                this.$subtitleCallback = function1;
                this.$callback = function12;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                Continuation<Unit> anonymousClass6 = new AnonymousClass6(this.$subtitleCallback, this.$callback, continuation);
                anonymousClass6.L$0 = obj;
                return anonymousClass6;
            }

            public final Object invoke(Element element, Continuation<? super Unit> continuation) {
                return create(element, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object $result) {
                String link;
                Element gofileAnchor = (Element) this.L$0;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        String link2 = gofileAnchor.attr("href");
                        if (StringsKt.contains$default(link2, "gofile", false, 2, (Object) null)) {
                            this.L$0 = SpillingKt.nullOutSpilledVariable(gofileAnchor);
                            this.L$1 = SpillingKt.nullOutSpilledVariable(link2);
                            this.label = 1;
                            if (new Gofile().getUrl(link2, "", this.$subtitleCallback, this.$callback, (Continuation) this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            link = link2;
                        }
                        return Unit.INSTANCE;
                    case 1:
                        link = (String) this.L$1;
                        ResultKt.throwOnFailure($result);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    @NotNull
    public final String getBaseUrl(@NotNull String url) {
        try {
            URI it = new URI(url);
            return it.getScheme() + "://" + it.getHost();
        } catch (Exception e) {
            return "";
        }
    }
}
