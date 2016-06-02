package main.java;



abstract class rk4 {
int n;
public double dydx[];
public double y[],yout[];
public void init(int dim,boolean self){
n=dim;
dydx=new double[n];
yout=new double[n];
if(self)y=yout;
else y=new double[n];
}

public abstract void derivs(double x,double y1[],double dydx[]);
public void next(double x,double h){ 
core(x,h);
}

public void nextmove(double h,double[] yin){
y=yin;
core(0.,h);
}
public void nextmove(double h){
core(0.,h);
}
private void core(double x, double h){

int i;
double xh,hh,h6;
double dym[]=new double[n];
double dyt[]=new double[n];
double yt[]=new double[n];
hh=h*0.5;
h6=h/6.0;
xh=x+hh;
derivs(xh,y,dydx);
for (i=0;i<n;i++) yt[i]=y[i]+hh*dydx[i];
derivs(xh,yt,dyt);
for (i=0;i<n;i++) yt[i]=y[i]+hh*dyt[i];
derivs(xh,yt,dym);
for (i=0;i<n;i++) {
yt[i]=y[i]+h*dym[i];
dym[i] += dyt[i];
}
derivs(x+h,yt,dyt);
for (i=0;i<n;i++)
yout[i]=y[i]+h6*(dydx[i]+dyt[i]+2.0*dym[i]);
}
}