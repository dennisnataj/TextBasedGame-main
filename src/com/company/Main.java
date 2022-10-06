package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //System Objects
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        //Game Variables
        String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin"};
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;

        //Player Variables
        int playerHealth = 100;
        int playerAttackDamage = 50;
        int numHealthPotions = 3; //Health Potions The Player Starts With
        int healthPotionHealAmount = 30; //Potion Heals Player With 30
        int healthPotionDropChange = 50; //Percentage 50% To Drop Potion

        boolean running = true; // Game Will Keep Running

        System.out.println("Welcome To The Dungeon");

        //Game is the name of the while loop
        GAME:
        while (running) {
            System.out.println("-----------------------------------------------");

            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println("\t# " + enemy + " has appeared! #\n");

            //Player can choose what to do
            while (enemyHealth > 0) {
                System.out.println("\tYour HP: " + playerHealth);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink Health Potion");
                System.out.println("\t3. Run!");

                String input = in.nextLine();
                if (input.equals("1")) {
                    int damageDealt = rand.nextInt(playerAttackDamage); //Random Damage 0-50
                    int damageTaken = rand.nextInt(enemyAttackDamage); //Random Damage 0-25

                    enemyHealth -= damageDealt; //Enemy Health Gets Low When Player Attacks
                    playerHealth -= damageTaken; //Player Health Gets Low When Enemy Attacks

                    System.out.println("\t> You Strike the " + enemy + " for " + damageDealt + " damage.");
                    System.out.println("\t> You receive " + damageTaken + " in retaliation!");

                    if (playerHealth < 1) {
                        System.out.println("\t> You have taken too much damage, you are too weak to go on!");
                        break;
                    }
                } else if (input.equals("2")) {
                    if (numHealthPotions > 0) { //Check if there is a health potion available
                        playerHealth += healthPotionHealAmount; //Heals the player
                        numHealthPotions--; //Minus one health potion from the collection
                        System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHealAmount + "."
                                + "\n\t> You now have " + playerHealth + " HP."
                                + "\n\t> You have " + numHealthPotions + " health potions left.\n"
                        );
                    } else {
                        System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!");
                    }
                } else if (input.equals("3")) {
                    System.out.println("\tYou run away from the " + enemy + "!");
                    continue GAME; //Breaks out the loop and finds a new enemy
                } else {
                    System.out.println("\tInvalid command!");
                }
            }

            if (playerHealth < 0) { //If player health is less than 0
                System.out.println("You limp out of the dungeon, weak from battle.");
                break;
            }

            System.out.println("-----------------------------------------------");
            System.out.println(" # " + enemy + " was defeated! # ");
            System.out.println("# You have " + playerHealth + "HP left. #");
            if (rand.nextInt(100) < healthPotionDropChange) { //Potion drop chance
                numHealthPotions++; //Adds one health potion
                System.out.println(" # The " + enemy + " dropped a health potion! # ");
                System.out.println(" # You have " + numHealthPotions + " health potion(s). # ");
            }
            System.out.println("-----------------------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("1. Continue fighting");
            System.out.println("2. Exit dungeon");

            String input = in.nextLine();

            while (!input.equals("1") && !input.equals("2")) { // Not equal to 1 or 2
                System.out.println("Invalid command!");
                input = in.nextLine();
            }

            if (input.equals("1")) {
                System.out.println("You continue on your adventure!");
            } else if (input.equals("2")) {
                System.out.println("You exit the dungeon, successful from your adventures!");
                break;
            }
        }
        System.out.println("#######################");
        System.out.println("# THANKS FOR PLAYING! #");
        System.out.println("#######################");

    }
}
