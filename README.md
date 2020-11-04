# Mirror game
This is a game that consists of shooting lasers rays on a board, on the board there are hidden mirrors that make the laser ray change direction, the objective of the game is to guess where the mirrors are by seeing where the laser beam enters and where it leaves.
At the beginning, the user chooses what size the board is going to be and how many hidden mirrors there are in it. To shoot a laser beam you have to give the coordinates where it is going to enter and if it is going to enter through a corner you have to say whether it enters vertically or horizontally.
The grid where the ray enters will be marked with an S and where it leaves will be marked with an E. If the ray enters and leaves through the same grid then it will be marked with a J.

Example:
For create the board the user have to enter the information in this way: nickname numberOfColumns numberOfRows numberOfMirrors.
So if the user introduces: pinkie 3 3 2, then will be created this board:

```
   A  B  C
1 [ ][/][ ]
2 [\][ ][ ]  The mirrors are created alleatory, in this example they are visible to explains how the lasers move, but in the game they are hiding.
3 [ ][ ][ ]
```

After if the user shoot a laser in 1AH, then will be printed on console:
```
[S][E][ ]
[ ][ ][ ]  
[ ][ ][ ]
```

If the user shoot a laser in 2C, then will be printed on console:
```
[E][ ][ ]
[ ][ ][S]  
[ ][ ][ ]
```
When the user wants to say where is a mirror, he or she will have to enter the coordinates in this way L+coordinate+position of the mirror(R/L) without spaces.
If the user types in L1BR, then will be printed on console:
```
[ ][/][ ]
[ ][ ][ ]  
[ ][ ][ ]
```
The game is over when the user finds all the hiding mirrors or when he or she types in: menu.
You can find the documentation of this game [here](https://github.com/Juan-dev123/mirror-game/blob/master/docs/requerimientos%20funcionales%20y%20diagrama%20de%20clases.pdf).

`The game was developed in java with the IDE Eclipse`
