# GrapeVine
A twitter bot framework and application in Java for Java 8.

Planned features:

* Add bots in format email:password
* Add bots in format from https://github.com/rhiever/TwitterFollowBot
* Bots stored in sqlite 3 db
* Scrape twitter for trending hashtags (avoid api calls, parse html)
* Bots attack with trending topic hashtags mixed with custom hashtags
* Bots retweet eachothers posts, and post the same post (randomly one or the other.)
* Bots reply to @bot comments with @sender
* Tweets stored in DB
* Bots randomly follow people, followers for each bot stored in db
* Bots randomly post to who they are @following once, list for each bot stored in db, selected at random

* Ability to add http and socks proxy lists for bots to post
* Ability to detect banned bots, rate limiting etc.

* Any other features that can be thought of.


* Automatically log in OAuth via automated HTTP request.

Longer term:
* Integrated JMegaHal Markov text generation
* PHP Web UI and socketed connetion on localhost for c&c.
* deeplearning4j neural network weighted markov statistical learning of some kind.
* deeplearning4j neural network weighted distributed average random seed for markov uniqueness.


requirements:
maven 2 or 3
official jdk 8
sqlite-jdbc-3.8.11.1.jar

instructions:

compile: mvn clean install

run: cd to target directory
download sqlite-jdbc-3.8.11.1.jar to target directory
java -cp ".:GrapeVine-0.1a-SNAPSHOT.jar:sqlite-jdbc-3.8.11.1.jar" org.speakeasy.grapevine.Main [args]
