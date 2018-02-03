package com.company;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, WordSetDesyncException {

        VsmIndex vsmIndex = new VsmIndex("testdir");

        for (TextData td : vsmIndex) {

            System.out.print(td.getName()+": ");

            for (Integer i : td.getTfVector()) {
                System.out.print(i+" ");
            }
            System.out.println();
        }


    }
}
