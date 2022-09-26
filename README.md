# Custom RegEx Engine

#### swagger ui
* [swagger](http://localhost:8080/swagger-ui/index.html)

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
```
