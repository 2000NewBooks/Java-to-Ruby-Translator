##2/5/2016
Today we began writing a class that can determine if the code inputted by the user is actually Ruby, so that the translator can work.
If it isn't, the program will throw some exceptions at them. We also began relearning Ruby, so that we can know what different Ruby
code snippets can be replaced by Java code, and retain the same functionality that the original Ruby program had. This will most
likely take quite a bit of time and testing, as we have to think of all possible Ruby keywords that a user might have used in their
program.

##2/11/2016
Today I worked more on relearning Ruby, and realized how different of a programming language it is from java. I also now realize how
hard it will be to do the translator, as we have to be able to handle anything the user puts in, whether its actually code or not. We
are getting fairly close to finishing our class that can recognize actual Ruby code. After this is complete, we will begin making the
main translator class that will replace the Ruby commands with java code that can do the same thing.

##2/18/2016
Today I continued relearning Ruby, and started a list of Ruby commands that our program must recognize, and what java commands should
replace them, like puts "" -> System.out.println(""). We have done a fair amount on the actual translator class, and should be able
to complete our first deliverable on time (February 26), which are methods that can recognize Ruby loops and statements, and convert
them to the appropriate java loops and statements. However, we will most likely still need to do quite a bit of bugfixing for it to
work.

##2/26/2016
Today we tested our program, making sure that it actually is able to translate Ruby loops (do, until, while, for), to java loops. This
was pretty difficult, as we had to make the program find the file and read it line-by-line while outputting it to a new file. We also
added the simple class Variable, which we use to store the name and type of the variables in the Ruby program, so that we can create
them as java variables later. We will most likely just make eveything a state variable, as trying to keep the scope would be rather
hard.

##3/4/2016
Today I continued learning Ruby, figuring out how different commands run so that we can have our program create java code that does
the same thing. We also added the ability to translate things like 'puts' and implemented the equivalent of 'gets.chomp'. We will
still need to edit the 'puts' translator however, as it doesn't always work if the Ruby code is using puts to print a variable. This
shouldn't be too difficult to fix though.

##3/11/2016
Today I continued working on the Ruby codecademy course, and added on a few things to my list of what Ruby commands we have to
replace, and with what java thingies. We are pretty far on our way toward our next deliverable, which is "Recognize the entirety of
Ruby". This would probably take quite a long time to actually complete, but we should have all of the commands that will be likely to
show up in someone's Ruby code that they'd like to translate.

##3/18/2016
Today I continued working on the codecademy Ruby course, and began implementing a multi-dimensional array translator, as they are
pretty similar in java and Ruby since they both use a temporary variable to terate through the array. I also tried to make a
translator for the .each method in Ruby, by just making an iterator for() loop, and attempted to implement the java version of a Ruby
for loop with start..end or start...end.

##3/25/2016
I pretty much didnt do anything, since it was spring break, and I was too lazy.

##4/1/2016
Today I added functionality for the transation of the basic operations + - * / %, made it so it actually does output the translated
code to a new file, which it prompts the user to name. I also added a bunch of comments to make the code easier to understand for
people looking at it, and added a toString() method for the Variable class to make the final creation of variables easier.

##4/8/2016
Today we added a few more translations for various keywords, and added some comments to make the code easier to read. We also started
trying to implement translations for class and method declarations. We started translations for Ruby comments as well, which is pretty
easy, and finished the translations for all the operations. We were easily on time with our deliverable last week.

##4/15/2016
Today we fixed some problems with it by adding some spaces where we had forgotten to earlier, in between added parts of Strings
mostly. I also started to make the variable declarations, by printing out all of the Variable objects we created toString() method. We
just have all variables declared as public at the moment, although we will have to figure out a better way to that later. Also, I
found that array declarations don't work too well yet.

##4/22/2016
Today I continued on the codecademy Ruby course, and realized we need to start working on hashes. This shouldnt't be too hard to
implement though, as it's similar in Ruby and java, but will probably be a significant amount of work still. We are pretty well on our
way to our deliverable, although our FileWriter writer is throwing an exception that it wasn't before, so we need to figure out why,
and fix the problem. It's kind of annoying however, cause we didn't even change anything in the method it's in.

##5/6/2016
Today we worked on finishing touches for the deliverable, which is to recognize 'all of Ruby'. We technically don't have ALL of Ruby
done, cause that would take quite a while, but we have all the most used keywords, and as many other random ones as we could find for
it to recognize. Andrew recently implemented Hash recognition, although it doesn't actually translate them at this point. We may or
may not implement hash translations, as there are quite a few things to add, and some are quite different from Java to Ruby. Our
deliverable is completely done, and we have even done translations for all keywords besides Hashes.

##5/13/2016
We did some bug fixing, and I tested it a few times with different things. It worked decently well, although the final code threw a
few compiletime errors, which we had to fix. These were mostly missing semicolons, unmatched brackets and stuff like that. I also
added translations for simple comments just using a '#', which I don't know how we managed to forget these earlier (Either that or I'm
just blind and now we have two comment translators).

##5/20/2016
We finished our powerpoint and script for the presentation to do on Wednesday. We are pretty prepared, and we may pass the 10 minute mark. We also created a test Ruby file that we will use for our demonstration. We've tested it a few times, and it works quite well. This should be added to GitHub soon.
