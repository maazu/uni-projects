
#### Speedclock: A speaking desktop clock 
[Features Directory](https://github.com/maazu/Java/tree/master/Extreme%20Programming%20Team%20Project/src/main/java/cethreetwenty/two)

Add your development guide and setup guide here...

The aim is to quickly help developers set up the project for development once they have checked out the svn repository for the first time
and to serve as a point of reference/single source of truth for specific details.

#### Suggested development workflow
Steps: Accept a 'trac' ticket > `svn update` > [technical work start] > `svn update` > `svn commit -m "[[ticket number]: description of work done]"`

Justification: The first few steps should be self explanatory, but the second use of `svn update` is to assure that any merge conflicts are resolved locally before committing changes to the master copy.



#### The Project:

In today’s world, we all need reminders of events like lectures, meetings, deadlines, etc. However, there are many tasks (e.g., checking your email, researching a topic on the Web or having a peek at Facebook) where we can become absorbed and not notice that we are spending more time than anticipated on these tasks.
In this project you will create a desktop application that will act as a clock with speaking functionality, in the sense that it will tell the time at specific intervals.
Users will be able to decide when to enable this functionality, to set the talking schedule (e.g., the interval between reminders), the volume, and other characteristics of the speaking clock (e.g., font size, voice timbre or even spoken language perhaps!).
The program could also monitor ambient sound volume (e.g., if Spotify is concurrently playing music) using the microphone or mixer information to decide whether to increase the volume of the speaking clock.
Ideally the clock should integrate with your calendar so that it reminds you of scheduled events as well.


#### Project Decomposition
This page will include a breakdown of the project into key features.
Desktop Application that acts a clock.
Speaking functionality
User can customise the application, this includes; talking schedule, volume, font size, voice timbre and maybe the language that is spoken.
The clock should be able to integrate with your calendar and then reminders are generated about the events in the calendar.
Could Monitor sound volume using microphone to decide whether the volume will need increasing.


####  Discussed features for 'Speeclock'
###### Clock
- Minimal interface showing the time and date.
- By default, the time should be shown as it is at their location. Either by using System or Locale properties.
###### Feature improvements
- Add ability to expand the minimal interface when the user intends to change their preferences (See feature: Settings menu/interface).
- Add ability to represent time and date based on time zone preferences.
######  TTS (Text-To-Speech)
- Allow time to be read out at different time intervals by TTS.
###### Settings menu/interface
- Easy to find settings 'button' in the interface to show the available user config options. Should include the following items in early iterations: mute/unmute, volume control.
###### Feature improvements
- Add ability to link google calendar so that appointment alerts can also be used by the TTS.
- Configure the above feature to allow the user to specify how long before their appointment they would like to be notified.
- Localisation: allow user to configure timezone for the clock.
- Internationalisation: change language the the clock speaks in.
- Volume control: Supply a volume slider to allow the user to configure application volume. This improvement is to establish independent 'speeclock' specific volume.
- Visual settings: change clock font/font size/colour (for digital time representations) and application pane background colour.
- Possibly allow internationalisation to dictate the language of any text in the application settings options as well.
###### Categorise settings options
Provide tabs that categorise options into settings menu sub-interfaces. Category examples may include (but are not limited to): Sound, Visual, TTS, Language, Timezone
