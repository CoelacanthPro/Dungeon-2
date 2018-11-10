/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eels.and.escalators;

import java.awt.Color;

public class Player {
    public static Player currentPlayer;
    private static Player players[] = new Player[4];
    
    public static void reset(){
        if (players[0] == null) {
           players[0] = new Player();
           players[1] = new Player();
           players[2] = new Player();
           players[3] = new Player();
        }
       currentPlayer = players[0];
    }
    Player(){
    }
    
    public static void switchTurn(){
        if(players[0] == currentPlayer){
            currentPlayer = players[1];
        }else if(players[1] == currentPlayer){
            currentPlayer = players[2];
        }else if(players[2] == currentPlayer){
            currentPlayer = players[3];
        }else if(players[3] == currentPlayer){
            currentPlayer = players[0];
        } 
    }
    public static Player getPlayerIndex(int index){
        return(players[index]);
    }
    public static Player[] getPlayers(){
        return(players);
    }
}
