# Map Tracker Back-End
See [here](https://github.com/teamlead-agbaje/map-tracker) for information on map tracker simulation.

This application is the back-end for the above application. It is compartible with any J2EE Application Server / Servlet container.

Basically, it processes a file that contains latitude-longitude coordinates, formats them appropriately, stores them in a singleton cache (no such thing, officially, but that's what I choose to call it. It's just a singleton, really), and feeds them to any caller via REST web-services.
