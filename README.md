# Project 1: A* on OpenStreetMap for Cyclists

Original code developed by Yudong (William) Xu.
Updated and edited by Jialuo (Eirc) Chen

Route planning application on OpenStreetMap for cyclists.

## How to run:

Run *main.Demo.java* to start the application.

Within the application: 
* Use left mouse button to pan.
* Use mouse scroll wheel to zoom.
* Use right mouse button to set start/destination way point, and to clear existing way points.


### Map configuration options:

Demo.java is set to run for toronto.osm which will also be used to autograde your code, it will take
a few minutes to load. For easier development, we have included a smaller snapshot of Toronto (near the U. of Toronto) in file "toronto_small.osm"

**Toy maps for debugging:**
We have also included three toy maps each containing very few nodes to help you debug your algorithm. Read [toymapsinfo.pdf](toymapsinfo.pdf) for more details.


To run for other OpenStreetMap locations in the world, 
download the area from the open street map website, put the .osm file under /data and 
change the osmFile variable in main method accordingly. 
(Note: the cyclist dataset is only relevant for toronto.)




## Data sources:
osm files can be exported from the official openStreetMap website
https://www.openstreetmap.org/export

Cycling safety data for toronto can be downloaded from https://data.torontopolice.on.ca/pages/traffic


WARNING: NO LICENSE, means no copy, no commercial usage and anything using my code without my permission not permitted.


