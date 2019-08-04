#include<stdio.h> 
#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

class Graph
{
public:
	int n;
	int start;
	int count = 0;
	vector<vector<int>> a;
	vector <int> path;
	bool hamCycle(ofstream &o);
	void printSolution(ofstream &o);
	bool hamCycleUtil(int pos);
	bool isSafe(int v, int pos);
};

bool Graph::isSafe(int v, int pos)
{
	if (a[path[pos - 1]][v] == 0)
		return false;

	for (int i = 0; i < pos; i++)
		if (path[i] == v)
			return false;
	return true;
}

bool Graph::hamCycleUtil(int pos)
{
	if (pos == n)
	{
		if (a[path[pos - 1]][path[0]] > 0)
			return true;
		else
			return false;
	}

	for (int v = 0; v < n; v++)
	{
		if (isSafe(v, pos))
		{
			path[pos] = v;
			if (hamCycleUtil(pos + 1) == true)
				return true;
			path[pos] = -1;
		}
	}
	return false;
}

bool Graph::hamCycle(ofstream &o)
{
	path.resize(n);
	for (int i = 0; i < n; i++)
		path[i] = -1;

	path[0] = start;
	if (hamCycleUtil(1) == false)
	{
		o << "NULL";
		return false;
	}

	printSolution(o);
	return true;
}

void Graph::printSolution(ofstream &o)
{
	for (int i = 0; i < n; i++)
	{
		if (i == n - 1)
			count += a[path[i]][path[0]];
		else
			count += a[path[i]][path[i + 1]];
	}
	o << count << endl;
	for (int i = 0; i < n; i++)
		o << path[i] << " ";
	o << path[0];
}
 
int main(int argc, char *argv[])
{
	if (argc < 3)
	{
		cout << "REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE" << "\n";
		return 0;
	}
	int V;
	int st;
	string ff(argv[1]);
	string fo(argv[2]);
	ifstream f(ff);
	if (!f) {
		return 0;
	}
	f >> st;
	f >> V;
	Graph graph;
	graph.n = V;
	graph.start = st;
	graph.a.resize(V, vector<int>(V));
	for (int i = 0; i < graph.n; i++)
	{
		for (int j = 0; j < graph.n; j++)
		{
			f >> graph.a[i][j];
		}
	}
	f.close();
	ofstream o(fo);
	graph.hamCycle(o);
	o.close();
	return 0;
}