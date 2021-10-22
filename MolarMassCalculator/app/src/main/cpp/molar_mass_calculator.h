//
// Created by Shahriar Nasim Nafi on 11/10/21.
// Copyright © 2021 Shahriar Nasim Nafi. All rights reserved.
//

#ifndef MOLAR_MASS_CALCULATOR_MOLAR_MASS_CALCULATOR_H
#define MOLAR_MASS_CALCULATOR_MOLAR_MASS_CALCULATOR_H

#endif //MOLAR_MASS_CALCULATOR_MOLAR_MASS_CALCULATOR_H


#include <vector>
#include <string>

using namespace std;

struct Element {
    string symbol;
    double mass;

    Element(string symbol, double mass);
};

struct ElementInfo {
    string name;
    int number = 0;

    ElementInfo(string name, int number);
};


class MolarMassCalculator {
private:
    string formula;
    bool wrongFormula = false;
    double mass;
    vector<ElementInfo> elementInfos;
    vector<Element> elements = {
            Element("H", 1.0079), Element("He", 4.0026), Element("Li", 6.941), Element("Be", 9.0122), Element("B", 10.811),
            Element("C", 12.0107), Element("N", 14.0067), Element("O", 15.9994), Element("F", 18.9984),
            Element("Ne", 20.1797), Element("Na", 22.9897), Element("Mg", 24.305), Element("Al", 26.9815),
            Element("Si", 28.0855), Element("P", 30.9738), Element("S", 32.065), Element("Cl", 35.453),
            Element("Ar", 39.948), Element("K", 39.0983), Element("Ca", 40.078), Element("Sc", 44.9559),
            Element("Ti", 47.867), Element("V", 50.9415), Element("Cr", 51.9961), Element("Mn", 54.938),
            Element("Fe", 55.845), Element("Co", 58.9332), Element("Ni", 58.6934), Element("Cu", 63.546),
            Element("Zn", 65.39), Element("Ga", 69.723), Element("Ge", 72.64), Element("As", 74.9216), Element("Se", 78.96),
            Element("Br", 79.904), Element("Kr", 83.8), Element("Rb", 85.4678), Element("Sr", 87.62), Element("Y", 88.9059),
            Element("Zr", 91.224), Element("Nb", 92.9064), Element("Mo", 95.94), Element("Tc", 98), Element("Ru", 101.07),
            Element("Rh", 102.9055), Element("Pd", 106.42), Element("Ag", 107.8682), Element("Cd", 112.411),
            Element("In", 114.818), Element("Sn", 118.71), Element("Sb", 121.76), Element("Te", 127.6),
            Element("I", 126.9045), Element("Xe", 131.293), Element("Cs", 132.9055), Element("Ba", 137.327),
            Element("La", 138.9055), Element("Ce", 140.116), Element("Pr", 140.9077), Element("Nd", 144.24),
            Element("Pm", 145), Element("Sm", 150.36), Element("Eu", 151.964), Element("Gd", 157.25),
            Element("Tb", 158.9253), Element("Dy", 162.5), Element("Ho", 164.9303), Element("Er", 167.259),
            Element("Tm", 168.9342), Element("Yb", 173.04), Element("Lu", 174.967), Element("Hf", 178.49),
            Element("Ta", 180.9479), Element("W", 183.84), Element("Re", 186.207), Element("Os", 190.23),
            Element("Ir", 192.217), Element("Pt", 195.078), Element("Au", 196.9665), Element("Hg", 200.59),
            Element("Tl", 204.3833), Element("Pb", 207.2), Element("Bi", 208.9804), Element("Po", 209), Element("At", 210),
            Element("Rn", 222), Element("Fr", 223), Element("Ra", 226), Element("Ac", 227), Element("Th", 232.0381),
            Element("Pa", 231.0359), Element("U", 238.0289), Element("Np", 237), Element("Pu", 244), Element("Am", 243),
            Element("Cm", 247), Element("Bk", 247), Element("Cf", 251), Element("Es", 252), Element("Fm", 257),
            Element("Md", 258), Element("No", 259), Element("Lr", 262), Element("Rf", 261), Element("Db", 262),
            Element("Sg", 266), Element("Bh", 264), Element("Hs", 277), Element("Mt", 278), Element("Ds", 281),
            Element("Rg", 282), Element("Cn", 285), Element("Nh", 286), Element("Fl", 289), Element("Mc", 290),
            Element("Lv", 293), Element("Ts", 294), Element("Og", 294),
    };
    vector<int> elementIndex = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    vector<int> elementNumber = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    const string number = "0123456789";
    string elementS;

    int findElementIndex(string text);

    int parseInt(string integer);

    bool parse();

    double calculateMass();

    void makeElementDict();

    int elementAlreadyExist(string &name);

    void clearData();

public:
    MolarMassCalculator();

    string getMolarMass(string formula);

    vector<ElementInfo> getResult();

    double getElementMass(int index);

    void performCalculation(string formula);

    double getMolarMass();

    bool isWrongFormula();

    double getElementMolarMassBySymbol(string symbol);
};