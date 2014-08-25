Requires a Rest Servlet running, e.g. run java-rest-servlet in maven jetty plugin

To run:

run (post | get | delete | options) -d <request parameters comma separated -h <headers comma separated> <url>
at minimum you should specify action(post, get, delete, options) and url

e.g. post -d myparameter=MyParameter -h myheader=MyHeader http://localhost:8080/java-rest-servlet/