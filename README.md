# Custom RegEx Engine

#### Example usage
```java
    @Autowired
    private Pattern pattern;

    @Override
    public void run(String... args) throws Exception {
        Matcher matcher = pattern.compile("a*bcd?xp gu...n[abc]4");
        boolean result = matcher.matches("abcdxp gurkanb4");
        System.out.println("Result : " + result);
    }
```

#### Engine
Create a route from regex string. Example in the below.

```text
Regex : g...an
Input : gurkan
-----------------------------------
StringMatcher(prefix=g)                                                 MatchResponse(result=true, index=1)                                   
DotMatcher(pattern=., prefix=, suffix=)                                 MatchResponse(result=true, index=1)                                   
DotMatcher(pattern=., prefix=, suffix=)                                 MatchResponse(result=true, index=1)                                   
DotMatcher(pattern=.an, prefix=, suffix=an)                             MatchResponse(result=true, index=3)                                   
LengthMatcher()                                                         MatchResponse(result=true, index=0)                                   
-----------------------------------


Regex : a*bcd?xp gu...n[abc]4+55
Input : abcdxp gurkanb4444444455
-----------------------------------
StarMatcher(pattern=a*bc, prefix=a, suffix=bc)                          MatchResponse(result=true, index=3)                                   
QuestionMatcher(pattern=d?xp gu, prefix=d, suffix=xp gu)                MatchResponse(result=true, index=6)                                   
DotMatcher(pattern=., prefix=, suffix=)                                 MatchResponse(result=true, index=1)                                   
DotMatcher(pattern=., prefix=, suffix=)                                 MatchResponse(result=true, index=1)                                   
DotMatcher(pattern=.n, prefix=, suffix=n)                               MatchResponse(result=true, index=2)                                   
GroupMatcher(pattern=[abc], prefix=[a, b, c], suffix=)                  MatchResponse(result=true, index=1)                                   
PlusMatcher(pattern=4+55, prefix=4, suffix=55)                          MatchResponse(result=true, index=10)                                  
LengthMatcher()                                                         MatchResponse(result=true, index=0)                                   
-----------------------------------
```
