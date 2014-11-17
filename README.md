Rogue-Like
==========

My first attempt at a RogueLike game

Added a map package with Map, Tile, and TileSet classes.
* [X] ~~They don't do anything right now because I'm met with a initialization error when trying to create the TileSets~~
* [X] Fixed the initialization error, rooms now render correctly (with proper set up)

**TODO**: 

* [ ] Map Generator 
	* [X] ~~fix the room generator~~
	* [X] add support for rectangular rooms
	* [X] create method to load room to a specific location on map
	* [X] *Bonus* added seed support for maps
	* [X] added the enum to store which face the wall piece is on (N, S, E, W, Middle, Corner)
	* [X] add method to determine where to place new room
	* [X] create hall generator
	* [X] create method to chain rooms ~~and halls~~ together
	* [ ] add method to detect wall intersections 
	* [ ] add method to adjust sprite for wall intersections
	* [ ] create map generator 

* [ ] View Mechanics
	* [ ] implement visible in Tile
	* [ ] implement solid in Tile (cannot see through solid tiles)