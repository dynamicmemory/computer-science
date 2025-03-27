class Vec {

public:
    Vec();
    Vec(int);
    Vec(const Vec&);
    Vec& operator=(const Vec&);
    ~Vec();
    double& operator[](int);
    

private:
    int array_limit;
    double* dp;
};
