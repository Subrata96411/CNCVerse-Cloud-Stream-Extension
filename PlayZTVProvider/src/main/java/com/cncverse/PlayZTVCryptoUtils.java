package com.cncverse;

import android.util.Base64;
import android.util.Log;
import java.util.Arrays;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: PlayZTVCryptoUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001$B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0005H\u0002J\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\u0002J\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\u0002J\u001a\u0010\u001f\u001a\u0004\u0018\u00010\u00052\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0014H\u0002J\u0012\u0010\"\u001a\u0004\u0018\u00010\u00052\b\u0010#\u001a\u0004\u0018\u00010\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R*\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\rj\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0019\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0018\u001a\u0004\b\u001a\u0010\u0016¨\u0006%"}, d2 = {"Lcom/cncverse/PlayZTVCryptoUtils;", "", "<init>", "()V", "TAG", "", "PLAYZ_AES_KEY", "PLAYZ_AES_IV", "PLAYZ_PRIMARY_AES_KEY", "PLAYZ_PRIMARY_AES_IV", "SUB_FROM", "SUB_TO", "SUB_REVERSE", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "decodeKey", "", "base64", "PRIMARY_KEY", "Lcom/cncverse/PlayZTVCryptoUtils$KeyInfo;", "getPRIMARY_KEY", "()Lcom/cncverse/PlayZTVCryptoUtils$KeyInfo;", "PRIMARY_KEY$delegate", "Lkotlin/Lazy;", "FALLBACK_KEY", "getFALLBACK_KEY", "FALLBACK_KEY$delegate", "decodeSubstitutionPayload", "value", "normalizeBase64", "decryptAes", "dataB64", "keyInfo", "decryptPlayZTV", "body", "KeyInfo", "PlayZTVProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PlayZTVCryptoUtils {

    /* JADX INFO: renamed from: FALLBACK_KEY$delegate, reason: from kotlin metadata */
    @NotNull
    private static final Lazy FALLBACK_KEY;

    @NotNull
    private static final String PLAYZ_AES_IV = "azVLNG5NOG1LbE5MN2wxNQ==";

    @NotNull
    private static final String PLAYZ_AES_KEY = "bTVLbDVuazR4SzFrTjdwTg==";

    @NotNull
    private static final String PLAYZ_PRIMARY_AES_IV = "MTRuTWs4bU41S2w1S0w3bA==";

    @NotNull
    private static final String PLAYZ_PRIMARY_AES_KEY = "Yi8xam1sNW5rNHg1azdwTg==";

    /* JADX INFO: renamed from: PRIMARY_KEY$delegate, reason: from kotlin metadata */
    @NotNull
    private static final Lazy PRIMARY_KEY;

    @NotNull
    private static final String SUB_FROM = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";

    @NotNull
    private static final String SUB_TO = "fFgGjJkKaApPbBmMoOzZeEnNcCdDrRqQtTvVuUxXhHiIwWyYlLsS";

    @NotNull
    private static final String TAG = "PlayZTVCrypto";

    @NotNull
    public static final PlayZTVCryptoUtils INSTANCE = new PlayZTVCryptoUtils();

    @NotNull
    private static final HashMap<Character, Character> SUB_REVERSE = new HashMap<>();

    private PlayZTVCryptoUtils() {
    }

    static {
        int length = SUB_TO.length();
        for (int i = 0; i < length; i++) {
            SUB_REVERSE.put(Character.valueOf(SUB_TO.charAt(i)), Character.valueOf(SUB_FROM.charAt(i)));
        }
        PRIMARY_KEY = LazyKt.lazy(new Function0() { // from class: com.cncverse.PlayZTVCryptoUtils$$ExternalSyntheticLambda0
            public final Object invoke() {
                return PlayZTVCryptoUtils.PRIMARY_KEY_delegate$lambda$0();
            }
        });
        FALLBACK_KEY = LazyKt.lazy(new Function0() { // from class: com.cncverse.PlayZTVCryptoUtils$$ExternalSyntheticLambda1
            public final Object invoke() {
                return PlayZTVCryptoUtils.FALLBACK_KEY_delegate$lambda$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: PlayZTVCryptoUtils.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/cncverse/PlayZTVCryptoUtils$KeyInfo;", "", "key", "", "iv", "<init>", "([B[B)V", "getKey", "()[B", "getIv", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "PlayZTVProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final /* data */ class KeyInfo {

        @NotNull
        private final byte[] iv;

        @NotNull
        private final byte[] key;

        public static /* synthetic */ KeyInfo copy$default(KeyInfo keyInfo, byte[] bArr, byte[] bArr2, int i, Object obj) {
            if ((i & 1) != 0) {
                bArr = keyInfo.key;
            }
            if ((i & 2) != 0) {
                bArr2 = keyInfo.iv;
            }
            return keyInfo.copy(bArr, bArr2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final byte[] getKey() {
            return this.key;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final byte[] getIv() {
            return this.iv;
        }

        @NotNull
        public final KeyInfo copy(@NotNull byte[] key, @NotNull byte[] iv) {
            return new KeyInfo(key, iv);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KeyInfo)) {
                return false;
            }
            KeyInfo keyInfo = (KeyInfo) other;
            return Intrinsics.areEqual(this.key, keyInfo.key) && Intrinsics.areEqual(this.iv, keyInfo.iv);
        }

        public int hashCode() {
            return (Arrays.hashCode(this.key) * 31) + Arrays.hashCode(this.iv);
        }

        @NotNull
        public String toString() {
            return "KeyInfo(key=" + Arrays.toString(this.key) + ", iv=" + Arrays.toString(this.iv) + ')';
        }

        public KeyInfo(@NotNull byte[] key, @NotNull byte[] iv) {
            this.key = key;
            this.iv = iv;
        }

        @NotNull
        public final byte[] getKey() {
            return this.key;
        }

        @NotNull
        public final byte[] getIv() {
            return this.iv;
        }
    }

    private final byte[] decodeKey(String base64) {
        return Base64.decode(base64, 0);
    }

    private final KeyInfo getPRIMARY_KEY() {
        return (KeyInfo) PRIMARY_KEY.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KeyInfo PRIMARY_KEY_delegate$lambda$0() {
        return new KeyInfo(INSTANCE.decodeKey(PLAYZ_PRIMARY_AES_KEY), INSTANCE.decodeKey(PLAYZ_PRIMARY_AES_IV));
    }

    private final KeyInfo getFALLBACK_KEY() {
        return (KeyInfo) FALLBACK_KEY.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KeyInfo FALLBACK_KEY_delegate$lambda$0() {
        return new KeyInfo(INSTANCE.decodeKey(PLAYZ_AES_KEY), INSTANCE.decodeKey(PLAYZ_AES_IV));
    }

    private final String decodeSubstitutionPayload(String value) {
        StringBuilder $this$decodeSubstitutionPayload_u24lambda_u240 = new StringBuilder();
        int length = value.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = value.charAt(i);
            Character ch = SUB_REVERSE.get(Character.valueOf(cCharAt));
            $this$decodeSubstitutionPayload_u24lambda_u240.append(ch != null ? ch.charValue() : cCharAt);
        }
        String restored = $this$decodeSubstitutionPayload_u24lambda_u240.toString();
        return new String(Base64.decode(normalizeBase64(restored), 0), Charsets.UTF_8);
    }

    private final String normalizeBase64(String value) {
        String normalized = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(value, "-", "+", false, 4, (Object) null), "_", "/", false, 4, (Object) null), "\n", "", false, 4, (Object) null), "\r", "", false, 4, (Object) null), " ", "", false, 4, (Object) null), "\t", "", false, 4, (Object) null);
        while (normalized.length() % 4 != 0) {
            normalized = normalized + '=';
        }
        return normalized;
    }

    private final String decryptAes(String dataB64, KeyInfo keyInfo) {
        try {
            byte[] cipherBytes = Base64.decode(normalizeBase64(dataB64), 0);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, new SecretKeySpec(keyInfo.getKey(), "AES"), new IvParameterSpec(keyInfo.getIv()));
            byte[] decrypted = cipher.doFinal(cipherBytes);
            return StringsKt.trim(new String(decrypted, Charsets.UTF_8)).toString();
        } catch (Exception e) {
            Log.e(TAG, "AES failed: " + e.getMessage());
            return null;
        }
    }

    @Nullable
    public final String decryptPlayZTV(@Nullable String body) {
        String raw;
        String fallback;
        String primary;
        String str;
        if (body == null) {
            raw = null;
        } else {
            try {
                raw = StringsKt.trim(body).toString();
            } catch (Exception e) {
                Log.e(TAG, "Decrypt error: " + e.getMessage(), e);
            }
        }
        if (raw == null) {
            raw = "";
        }
        boolean z = true;
        if (raw.length() == 0) {
            return null;
        }
        Log.d(TAG, "Raw Payload: " + raw);
        if (StringsKt.startsWith$default(raw, "{", false, 2, (Object) null) || StringsKt.startsWith$default(raw, "[", false, 2, (Object) null) || StringsKt.startsWith$default(raw, "<", false, 2, (Object) null)) {
            return raw;
        }
        try {
            String primaryPayload = decodeSubstitutionPayload(new Regex("\\s").replace(raw, ""));
            Log.d(TAG, "Primary payload decoded");
            primary = decryptAes(primaryPayload, getPRIMARY_KEY());
            str = primary;
        } catch (Exception e2) {
            Log.e(TAG, "Primary decrypt failed: " + e2.getMessage());
        }
        if (!(str == null || StringsKt.isBlank(str))) {
            Log.d(TAG, "Primary decrypt success");
            return primary;
        }
        try {
            fallback = decryptAes(new Regex("\\s").replace(raw, ""), getFALLBACK_KEY());
            String str2 = fallback;
            if (str2 != null && !StringsKt.isBlank(str2)) {
                z = false;
            }
        } catch (Exception e3) {
            Log.e(TAG, "Fallback decrypt failed: " + e3.getMessage());
        }
        if (!z) {
            Log.d(TAG, "Fallback decrypt success");
            return fallback;
        }
        Log.e(TAG, "All decryption strategies failed");
        return null;
        Log.e(TAG, "Decrypt error: " + e.getMessage(), e);
        return null;
    }
}
