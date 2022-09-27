# Custom RegEx Engine

#### Rest Request

POST heroku : https://young-shore-42260.herokuapp.com/api/v1/regex

POST - http://localhost:8080/api/v1/regex
```json
{
  "regex": "a*bcd?xp gu...n[abc]4+55",
  "text": "abcdxp gurkanb4444444455"
}
```

#### Example usage
```java
    @Autowired
    private Pattern pattern;

    @Override
    public void regex() throws Exception {
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
