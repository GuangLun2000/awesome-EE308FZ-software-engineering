#include <iostream>
#include <fstream>
#include <stack>
#include <vector>
#include <set>
#include <deque>
#include <algorithm>
using namespace std;

/**
 * @brief 
 * This cpp program is used to output some statistics and structures in the file.
 * based on Cpp 11
 * Implemented by Hanlin Cai.
 * MU  ID : 20122161
 * FZU ID : 832002117
 * 
 * URL: https://caihanlin.com
 */


typedef pair<string, int> key_level_pair;

const set<string> keywords = {"auto", "break", "case", "char", "const",
							  "continue", "default", "do", "double", "else",
							  "enum", "extern", "float", "for", "goto", "if",
							  "int", "long", "register", "return", "short",
							  "signed", "sizeof", "static", "struct", "switch",
							  "typedef", "union", "unsigned", "void",
							  "volatile", "while"};

bool isNotLetter(char x)
{
	return !((x >= 65) && (x <= 90) || (x >= 97) && (x <= 122) || (x == '{') || (x == '}'));
}


/* 
Return the total num of keywords in a code file
the path directed to.
*/
int countKeywords(vector<string> &code)
{
	int total_num = 0;
	for (int i = 0; i < code.size(); i++)
	{
		if (keywords.find(code[i]) != keywords.end())
		{
			total_num++;
		}
	}
	return total_num;
}


/*
Print the number of "switch case" structures, and output 
the number of "case" corresponding to each group
*/
void countSwitchAndCase(vector<string> &code)
{
	int switch_num = 0;
	int case_temp_num = 0;
	vector<int> case_num;
	for (int i = 0; i < code.size(); i++)
	{
		if (code[i] == "switch")
		{
			switch_num++;
			case_temp_num = 0;
			for (int j = i + 1; j < code.size(); j++)
			{
				if (code[j] == "switch")
				{
					break;
				}
				else
				{
					if (code[j] == "case")
					{
						case_temp_num++;
					}
				}
			}
			case_num.push_back(case_temp_num);
		}
	}
	cout << "switch num: " << switch_num << endl;
	cout << "case num: ";
	for (int i = 0; i < case_num.size(); i++)
	{
		cout << case_num[i] << " ";
	}
	puts("");
}


deque<string> extractIfElse(vector<string> &code)
{
	deque<string> if_else;
	for (int i = 0; i < code.size(); i++)
	{
		bool flag = (code[i] == "if" || code[i] == "else" || code[i] == "{" || code[i] == "}");
		if (flag)
		{
			if_else.push_back(code[i]);
		}
	}
	//if_else.pop_back();
	//if_else.pop_front();
	return if_else;
}


vector<key_level_pair> extractKeyLevel(vector<string> &code)
{
	int flag = 1;
	deque<string> if_else = extractIfElse(code);
	vector<key_level_pair> key_level_vec;
	for (int i = 0; i < if_else.size(); i++)
	{
		if (if_else[i] == "if" && if_else[i + 1] == "{" && if_else[i - 1] != "else" && if_else[i + 2] != "}")
		{
			key_level_pair temp = {"if", flag};
			key_level_vec.push_back(temp);
			flag++;
			// int j = i;
			// while (!(if_else[j]  == "else" && if_else[j + 3] != "}") )
			// {

			// }
		}
		if (if_else[i] == "if" && if_else[i + 1] == "{" && if_else[i - 1] != "else" && if_else[i + 2] == "}")
		{
			key_level_pair temp = {"if", flag};
			key_level_vec.push_back(temp);
		}
		if (if_else[i] == "else" && if_else[i + 3] != "}" && if_else[i + 1] != "if" && if_else[i + 1] == "{")
		{
			key_level_pair temp = {"else", flag};
			key_level_vec.push_back(temp);
			flag++;
		}
		if (if_else[i] == "else" && if_else[i + 3] == "}" && if_else[i + 1] != "if")
		{
			key_level_pair temp = {"else", flag};
			key_level_vec.push_back(temp);
			flag -= 1;
		}
		if (if_else[i] == "else" && if_else[i + 1] == "if" && if_else[i + 3] == "}" && if_else[i + 2] == "{")
		{
			key_level_vec.push_back({"else if", flag});
		}
		if (if_else[i] == "else" && if_else[i + 1] == "if" && if_else[i + 3] != "}" && if_else[i - 1] != "if")
		{
			key_level_vec.push_back({"else if", flag});
			flag++;
		}
	}
	return key_level_vec;
}


/*
Perfectly handling multiple nesting
*/
int countIfElse(vector<string> &code)
{
	int num = 0;
	vector<key_level_pair> key_level_vec;
	key_level_vec = extractKeyLevel(code);
	for (int i = 0; i < key_level_vec.size(); i++)
	{
		if (key_level_vec[i].first == "if")
		{
			for (int j = i; j < key_level_vec.size(); j++)
			{
				if (key_level_vec[j].first == "else if")
				{
					break;
				}

				if (key_level_vec[j].first == "else" && key_level_vec[i].second == key_level_vec[j].second)
				{
					num++;
				}
			}
		}
	}

	// for (int i = 0; i < key_level_vec.size(); i++)
	// {
	// 	cout << key_level_vec[i].first << " " << key_level_vec[i].second << endl;
	// }

	return num;
}


int countIfElseIfElse(vector<string> &code)
{
	int num = 0;
	vector<key_level_pair> key_level_vec = extractKeyLevel(code);
	bool has_else_if = false;
	for (int i = 0; i < key_level_vec.size(); i++)
	{
		if (key_level_vec[i].first == "if")
		{
			for (int j = i + 1; j < key_level_vec.size(); j++)
			{
				if (key_level_vec[j].first == "if" && key_level_vec[i].second == key_level_vec[j].second)
				{
					break;
				}
				if (key_level_vec[j].first == "else if" && key_level_vec[i].second == key_level_vec[j].second)
				{
					has_else_if = true;
				}
				if (key_level_vec[j].first == "else" && key_level_vec[i].second == key_level_vec[j].second)
				{
					if (has_else_if)
					{
						num++;
					}
				}
			}
			has_else_if = false;
		}
	}
	return num;
}


void read(string &path, int &level)
{
	cout << "Please type in the path if code file: ";
	puts("");
	cout << "The path should be written like: /Users/lancecai/Documents/GitHub/EE308FZ/Record-for-LAB/Lab1/ctest.txt" << endl;
	cin >> path;
	cout << "Please select the level of completion (1 to 4): ";
	cin >> level;
	if (level <= 0 || level > 5)
	{
		throw "Unexpected level number!";
	}
}


void readFile(string &path, vector<string> &code)
{
	ifstream file(path);
	string temp;
	while (file >> temp)
	{
		code.push_back(temp);
	}
	// for (int i = 0; i < code.size(); i++)
	// {
	// 	cout << code[i] << endl;
	// }
	//cout << code << endl;
}


void preprocess(vector<string> &code)
{
	for (int i = 0; i < code.size(); i++)
	{
		string temp = code[i];
		temp.erase(std::remove_if(temp.begin(), temp.end(), isNotLetter),
		temp.end());
		code[i] = temp;
	}
}


void solve(vector<string> &code, int &level)
{
	switch (level)
	{
	case 1:
		cout << "Case 1: Count keywords" << endl;
		cout << "total num: " << countKeywords(code) << endl;
		break;
	case 2:
		cout << "Case 2" << endl;
		countSwitchAndCase(code);
		break;
	case 3:
		cout << "Case 3" << endl;
		cout << "if-else num: " << countIfElse(code) << endl;
		break;
	case 4:
		cout << "Case 4" << endl;
		cout << "if-elseif-else num: " << countIfElseIfElse(code) << endl;
		break;
	// case 5:
	// 	cout << "Case 1: Count keywords" << endl;
	// 	cout << "total num: " << countKeywords(code) << endl;
	// 	cout << "Case 2" << endl;
	// 	countSwitchAndCase(code);
	// 	cout << "Case 3" << endl;
	// 	cout << "if-else num: " << countIfElse(code) << endl;
	// 	cout << "Case 4" << endl;
	// 	cout << "if-elseif-else num: " << countIfElseIfElse(code) << endl;
	default:
		break;
	}
}


int main()
{
	string path;
	vector<string> code;
	int level;
	read(path, level);
	readFile(path, code);
	preprocess(code);
	// for (int i = 0; i < code.size(); i++)
	// {
	// 	cout << code[i] << endl;
	// }
	solve(code, level);
	//countIfElse(code);
	return 0;
}

/**
 * Implemented by Hanlin Cai.
 * MU  ID : 20122161
 * FZU ID : 832002117
 */