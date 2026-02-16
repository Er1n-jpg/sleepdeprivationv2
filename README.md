This is a program that tracks sees if you have closed your eyes for more than a minute and emails your email of choice. Initially this project was made to counter the fact I kept on sleeping in class + keep me accountable
and it was also an excuse for me to make my very first project in OpenCV in a language i'm super familliar with!

You can access this project through downloading one of the .exe files on the releases page (*warning!! .exe's are only for windows this is NOT mac compatable sorry)

**How to navigate this project?**
When you first open the .exe file you will be greeted with the main screen which might look a little something like this!
<img width="1650" height="1080" alt="Blinked too long (1)" src="https://github.com/user-attachments/assets/8c6c1e0c-27f9-47cd-b8bc-2653d3c1fb80" />


This screen gives you 2 options, one of them is the "To-email" option which is mandatory and it will not let you continue to the camera stage without having entered a valid email. The second option
is the message area where you can customize the email sender message, this is optional and if you choose not to do it, the program will send a default email saying "user is asleep".

Once getting past the intial screen it will bring you to a silly loading screen where opencv needs to load, it will probably have some illustration I drew on there, if not it might just say loading (TBD). Once opencv is loaded you will see a reflection of your face (YES YOUR FACE) and you will see squares that track your face and eyes. To test the program out you literally just need to close your eyes and wait, if the opencv doesnt think something else on your face is magically your eye (sometimes it will), it will send an email to your person of choice through the email "Blinkedtoolong@gmail.com" (*IT MIGHT BE IN YOUR TRASH OR SPAM). 
