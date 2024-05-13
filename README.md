# Monopoly - Gruppo 01

[![Current Series](https://img.shields.io/badge/Latest_Series-SerieG3-green?style=for-the-badge)](https://gitlab-edu.supsi.ch/dti-isin/fabio.landoni/fondamenti_di_informatica/sp_2023_2024/Gruppo_01)
[![JDK Version](https://img.shields.io/badge/JDK-17-aa8664.svg?logo=oracle&style=for-the-badge)](https://www.oracle.com/java/technologies/downloads/#java17)
[![License](https://img.shields.io/badge/License-UNLICENSED-purple?style=for-the-badge)]()

This is a `Java`, CLI implementation of the game Monopoly.

## Features

As for *Series G3* the features are:
there are four players that play on a 11x11 board.

Every player has a name and a unique symbol, which are assigned at the start of the
game.

There is a bank, which manages payments and fees throughout the game.

The bank, initially has 1'000'000.–, every player is then given 2'000.–

The player move clockwise on the board, by rolling two D-6 dices and every
time they land on a cell the relative fee is applied.

There are 4 types of cells:
* The Start Cell, on which the player receives 100.– Bonus
* The Parking Cell, neuter
* The Propriety Cell, which has different names and colors, and applies fees to player on landing
    * Players can buy proprieties so they get the fee from other players
    * Players, once they have all proprieties of the same color, can build upon those proprieties.
        * At first the player can build an house per turn, up to four.
        * Once the user has built 4 houses on a propriety, if he wants to build further, he destroys them and builds an hotel (max 1 per propriety).
* The Taxes Cell, which apply a 10% Wealth fee (Wealth Tax) or 200.– (Luxury Tax)
* The Prison cell
    * Once a player is sent here, he has 3 turns to roll two twin values with the dices to get out, otherwise they pay a bailout.
* The Go-To Prison Cell, which sends players to prison

The game is over when three of the four players has lost all their money.

## Credits

* **Relator**: [Fabio Landoni](mailto:fabio.landoni@supsi.ch)
* **Developer**: [Ivo Herceg](mailto:ivo.herceg@student.supsi.ch)
* **Developer**: [Andrea Masciocchi](mailto:andrea.masciocchi@student.supsi.ch)
* **Developer**: [Luca Mazza](mailto:luca.mazza@student.supsi.ch)

## License

This project is unlicensed and owned internally by SUPSI.
