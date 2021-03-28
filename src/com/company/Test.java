package com.company;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class Test {
    public static void main(String[] args) {

        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        HashMap<Integer, Integer> hm = new HashMap<>();

        Pair<Integer, Integer> p = aaa();

        ArrayList<Pair<Integer, Integer>> moves = new ArrayList<>();
        moves.add(new Pair<>(1, 2));
        moves.add(new Pair<>(1, -2));
        ArrayList<Integer> a = new ArrayList<>();
        a.add(p.getFirst());
        hm.put(1,4);
        hm.put(1,4);
        //System.out.println(hm.);
    }

    static Pair<Integer, Integer> aaa (){
        return new Pair<>(1,3);
    }
}
