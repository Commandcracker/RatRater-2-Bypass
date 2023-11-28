# RatRater 2 Bypass

This Repository contains 2 bypasses for [RatRater2].

## ⚠️ Disclaimer

**This program is for educational purposes only, and I am not responsible for any of your actions!** \
**If you use this to create a rat then you're a discussing human being!**

## Explanation

Short explanation of booth bypasses.

### 1st bypass

Extracting token from `field_148258_c`.

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

## Solution

Solution for detecting the bypasses.

### 1st bypass

The 1st bypass can be fixed by searching for `field_148258_c` and marking it as a sessionID grabber.

### 2nd bypass

The 2nd bypass is a bit harder to detect, it needs runtime analysis.
It would need to watch if one of the above-mentioned functions or the field is used.

## Additional

Additional feedback/requests for [RatRater2].

### Classification: Quantiy (low confidence)

It goes away if the mod has moore then one class,
but I think it should check for the occurrence of empty and junk functions, and
it could also check if the file size is real.

### Discord websockets

I think the analyser should statically check for discord webhooks and 
in the runtime analysis for http requests to discord.

---

Booth bypasses can now be combined to create the ULTIMATE BYPASS jk :D

[RatRater2]: https://github.com/KTibow/RatRater2
