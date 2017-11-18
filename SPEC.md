# Project ClipClop
Software specification  
Andrew Blomenberg, 10/13/2017    
Status: Incomplete

## Overview
ClipClop is an expansion of the clipboard in Android. It will allow it's users to copy and paste more than one string at a time. ClipClop will remember the last few copies and allow the user to select which of them to paste.

## General specifications
| What | Priority | 
|-|-|-|-| 
| The application will have a name other than EVERGREEN | med | 
| The application will have an associated launcher icon that can be used without copyright claims. | med |
| The application will appear in the top ten apps listed when a user searches for “copy and paste” or “clipboard” in the Google play store. | low | 
| ClipClop will operate with full functionality on any phone running Android 4.2 or higher | high |
| ClipClop will operate with full functionality on any tablet running Android 4.2 or higher | low |  
| If ClipClop is installed on a Pixel XL, the resulting slowdown and battery drain will not be noticable | high | 
| ClipClop will be developed using the native Android libraries | high| 
| ClipClop will not be designed with Titanium or HTML | high | 
| ClipClop will not require an internet connection for any features | med | 
| ClipClop will not request any permissions that are not completely necessary for the functionality of the application. | low  | 

## Interface Specifications ClipClop has two primary interfaces: a buffer interface and a prefs interface.
### Buffer Interface
| Behavior | Priority |
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|-------------|--------------------| 
| The buffer interface will be accessible in no more than three taps from any application that does not hide the notification bar | high | 
| The buffer interface will not steal application focus from the current application, instead, the buffer interface will be accessed within the notification menu or within a floating menu | high |
| The buffer interface will provide the user with a list of most recent clippings. | high |
| The buffer interface will allow the user to pin a clipping. A pinned clipping will be accessible in the buffer interface until it is unpinned | low | 
| The buffer interface will only display strings that have been copied. Images will not be displayed. | high | not started | | 
| Clippings stored in the buffer interface will be truncated when they are too long to be entirely visible in the buffer menu. | high | 
| When a item is selected in the buffer interface, it will be placed into Andoid system clipboard and not directly pasted in | high | 
| When the user copies an item, the item will be diplayed in the buffer interface | high | 
| ClipClop will persistently display a notification that can be used to access the butter interface | high | 
| Pinned items will appear in a different list than the recently copied items | med | 

### Prefs Interface
| Behavior | Priority | 
|-|-|-|-| 
| ClipClop will have at least one configuration option.| med | 
| Configuration options will be accessible via a separate interface called “prefs.” | high | 
| The prefs interface will allow the user to select how many clippings to save in the buffer at one time | med | 
| The prefs interface will use a UI theme which is popular in other Android configuration menus | low| 
| The prefs interface will be as simple as possible while still presenting the required information | med | 

## User Stories
* ##### As a user I want to have a copied item saved to a persistent notifaction in order to use it later. 

* ##### As a user I want to view my history of copied items in order to pick an item from my clipboard history. 

* ##### As a user I want to change the number of stored recently copied items in order to cater the app to my need.

* ##### As a user I want to select a stored item to be put into their current clipbaord in order to save time and decrease key strokes.

* ##### As a user I want to pin favorite texts in order to access them quicker.


## Scenarios
**Jeff:** Jeff has gotten tired of typing "JeffGrunganhatestheveryideaofcheesecake@cheesecakehaters.org" into every email field, so he uses ClipClop to pin his email address. Now whenever he is prompted to enter his email address, he can quickly access it from any screen and paste it in.

**Betty:** Jeff has just sent betty his contact information over text. Betty wants to create a new contact for Jeff on her phone, so she copies "Jeff", "Grungan", "JeffGrunganhatestheveryideaofcheesecake@cheesecakehaters.org", and "Chief of Cheesecake Disliking" seperately while she is in her texting app. She then switches to her contacts app and uses ClipClop to paste in each of Jeff's different contact fields without having to switch between apps.

**Mark:** Mark downloads ClipClop because he is bored. He looks at the icon, the name, and the two interfaces and starts composing poetry because that is the only way he can adequately describe the simplicity and intuitive nature of the interfaces, the synergy of the name and icon, and the amazingly fast and responsive features.

## Wireframes

![Wireframe1](/wireframe1.PNG)
![Wireframe2](/wireframe2.PNG)
![Wireframe3](/wireframe3.PNG)
![Wireframe4](/wireframe4.PNG)


