# Trains Coding Kata: FruitRailwaysApp

## Problem Description:
The local commuter railroad services a number of towns in Kiwiland.
Because of monetary concerns, all of the tracks are 'one-way.' That is,
a route from Kaitaia to Invercargill does not imply the existence of a
route from Invercargill to Kaitaia. In fact, even if both of these routes
do happen to exist, they are distinct and are not necessarily the same
distance!
The purpose of this problem is to help the railroad provide its
customers with information about the routes. In particular, you will
compute the distance along a certain route, the number of different
routes between two towns, and the shortest route between two
towns.

## Solution:
Each town is vertice in a weighted directed graph. Each town stores information about it's outgoing edges in a HashMap, where town objects are the keys for the outgoing connections and the values represent the distance.  The RailwayRouter class is responsible for taking in String input which is parses and uses to build the towns, which it stores in a town HashMap, with the town name as the key and the town object as the value. Most of the RailwayRouter classes public methods take string input to represent a start and sometimes an end town when searching for routes. The string input for the town name can then be used to retrieve the town object from the RailwayRouter's towns, or it will throw an error if it was found that the input was not a valid town name in our existing graph. 

To build the project, navigate into the FruitRailwaysApp directory. Run with this command:
```
./gradlew build
```
The projects junit tests will run each time the app is built.

