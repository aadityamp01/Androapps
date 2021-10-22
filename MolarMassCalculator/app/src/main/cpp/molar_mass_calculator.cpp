//
// Created by Shahriar Nasim Nafi on 11/10/21.
// Copyright © 2021 Shahriar Nasim Nafi. All rights reserved.
//

#include "molar_mass_calculator.h"
#include<iostream>
#include <utility>
#include <vector>
#include <sstream>

using namespace std;


Element::Element(string symbol, double mass) : symbol(move(symbol)), mass(mass) {}

ElementInfo::ElementInfo(string name, int number) : name(move(name)), number(number) {}


MolarMassCalculator::MolarMassCalculator() {
    makeElementDict();
}

// find atomic number
int MolarMassCalculator::findElementIndex(string text) {
    int index = 0;
    string find = text;
    if (text.size() == 1) {
        find.append("*");
    }
    int x = elementS.find(find);
    index = x == -1 ? x : (x / 2) + 1;
    return index;
}

int MolarMassCalculator::parseInt(string integer) {
    stringstream toInt(integer);
    int retuned;
    toInt >> retuned;
    return retuned;
}

bool MolarMassCalculator::parse() {
    int c = 0;
    int x = 0;
    int y = 0;
    int c1 = 0;
    int marker1 = 0;
    int totalLoop = 0;
    int marker2 = -1;
    int multiplier = 1;
    int a1[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int a2[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    while (c <= formula.size() - 1 && !wrongFormula) {
        bool OK = true;
        int index = findElementIndex(formula.substr(c, 2));
        if (index == -1) {
            index = findElementIndex(formula.substr(c, 1));
            y = 1;
        } else {
            y = 2;
        }


        if (index > -1) {
            marker2 = -1;
            for (int c2 = 0; c2 <= 9; c2++) {
                if (a2[c2] == index) {
                    marker2 = c2;
                    break;
                }
            }

            if (marker2 > 0) {
                marker1 = marker2;
                totalLoop -= 1;
            } else {
                marker1 = totalLoop;
            }
            c += y;
            a2[marker1] = index;

            int c3 = 0;
            while (OK && c + c3 <= formula.size() - 1) {
                index = number.find(formula.substr(c + c3, 1));
                index >= 0 ? c3++ : OK = false;
            }

            if (c3 > 0) {
                if (marker2 >= 0) {
                    a1[marker1] += parseInt(formula.substr(c, c3)) * multiplier;
                } else {
                    a1[marker1] = parseInt(formula.substr(c, c3)) * multiplier;
                }
            } else {
                marker2 >= 0 ? a1[marker1] += (1 * multiplier) : a1[marker1] = (1 * multiplier);
            }

            c += c3;
            totalLoop++;
        } else {
            int zz = 0;
            string search = "[]()";
            index = search.find(formula.substr(c, 1));
            if (index == -1) {
                wrongFormula = true;
                break;
            }
            if (index == 0 || index == 2) {
                if (index == 2) {

                    for (x = c; x <= formula.size() - 1; x++) {
                        if (formula.substr(x, 1) == ")") {
                            zz = x;
                            break;
                        }
                    }
                } else {
                    for (x = c; x <= formula.size() - 1; x++) {
                        if (formula.substr(x, 1) == "]") {
                            zz = x;
                            break;
                        }
                    }
                }
                int c4 = 1;
                OK = true;
                while (OK && zz + c4 <= formula.size() - 1) {
                    int index1 = number.find(formula.substr(zz + c4, 1));
                    index1 >= 0 ? c4++ : OK = false;
                }
                if (c4 > 1) {
                    int z = parseInt(formula.substr(1 + zz, (c4 + zz) - (1 + zz)));
                    multiplier *= z;
                }
                c++;
            }
            if (index == 1 || index == 3) {
                int c5 = 1;
                OK = true;
                while (OK && c + c5 <= formula.size() - 1) {
                    int index1 = number.find(formula.substr(c + c5, 1));
                    index1 >= 0 ? c5++ : OK = false;
                }
                if (c5 > 1) {
                    multiplier = multiplier / parseInt(formula.substr(c + 1, c5 - 1));
                }
                c += c5;
            }
            if (index == 4) {
                int c6 = 1;
                OK = true;
                while (OK && c + c6 <= formula.size() - 1) {
                    int index1 = number.find(formula.substr(c + c6, 1));
                    index1 >= 0 ? c6++ : OK = false;
                }
                if (c6 > 1) {
                    multiplier = multiplier / parseInt(formula.substr(c + 1, c6 - 1));
                }
                c += c6;
            }
        }
    }

    for (int i = 0; i <= elementIndex.size(); i++) {
        elementIndex[i] = a2[i];
        elementNumber[i] = a1[i];
    }
    if (wrongFormula) {
        return false;
    }
    return true;
}

double MolarMassCalculator::calculateMass() {

    for (int i = 0; i <= elementIndex.size(); i++) {
        if (elementIndex[i] == 0) {
            break;
        }
        mass += elements[elementIndex[i] - 1].mass * elementNumber[i];
    }
    mass = (mass * 100) / 100;
    return mass;
}

void MolarMassCalculator::makeElementDict() {
    for (int i = 0; i <= elements.size(); i++) {
        elementS.append(elements[i].symbol);
        if (elements[i].symbol.size() == 1) {
            elementS.append("*");
        }
    }
}

int MolarMassCalculator::elementAlreadyExist(string &name) {
    for (int i = 0; i < elementInfos.size(); i++) {
        if (elementInfos[i].name == name) {
            return i;
        }
    }
    return -1;
}


string MolarMassCalculator::getMolarMass(string formula) {
    this->formula = formula;
    clearData();
    bool success = parse();
    calculateMass();
    for (int i = 0; i < elementIndex.size(); i++) {
        if (elementIndex[i] == 0) {
            break;
        }

        int index = elementAlreadyExist(elements[elementIndex[i] - 1].symbol);
        if (index != -1) {
            elementInfos[index].number += elementNumber[i];
        } else {
            ElementInfo elementInfo = ElementInfo(elements[elementIndex[i] - 1].symbol, elementNumber[i]);
            elementInfos.push_back(elementInfo);
        }
    }
    string result = "Molar Mass for ";
    if (success) {
        result.append(formula);
        result.append(": ");
        result.append(to_string(mass));
        return result;
    } else {
        return "ERROR";
    }
}

void MolarMassCalculator::performCalculation(string formula) {
    this->formula = formula;
    clearData();
    bool success = parse();
    calculateMass();
    if (success) {
        for (int i = 0; i < elementIndex.size(); i++) {
            if (elementIndex[i] == 0) {
                break;
            }

            int index = elementAlreadyExist(elements[elementIndex[i] - 1].symbol);
            if (index != -1) {
                elementInfos[index].number += elementNumber[i];
            } else {
                ElementInfo elementInfo = ElementInfo(elements[elementIndex[i] - 1].symbol, elementNumber[i]);
                elementInfos.push_back(elementInfo);
            }
        }
    }
}


vector<ElementInfo> MolarMassCalculator::getResult() {
    return elementInfos;
}

double MolarMassCalculator::getElementMass(int index) {
    return elements[index].mass;
}

double MolarMassCalculator::getMolarMass() {
    return mass;
}

bool MolarMassCalculator::isWrongFormula() {
    return wrongFormula;
}

void MolarMassCalculator::clearData() {
    mass = 0;
    wrongFormula = false;
    elementIndex = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    elementNumber = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    elementInfos.clear();
}

double MolarMassCalculator::getElementMolarMassBySymbol(string symbol){
    for (int i = 0; i < elements.size(); i++) {
        if (elements[i].symbol == symbol) {
            return elements[i].mass;
        }
    }
    return 0;
}