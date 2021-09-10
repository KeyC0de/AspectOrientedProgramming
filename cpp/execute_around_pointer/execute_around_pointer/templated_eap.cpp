#include <iostream>
#include <vector>


template<class NextAspect, class Para>
class Aspect
{
protected:
	Aspect( Para p )
		:
		m_para{p}
	{

	}
	Para m_para;
public:
	NextAspect operator->()
	{
		return NextAspect( m_para );
	}
};

template<class NextAspect, class Para>
struct Visualizing
	:
	Aspect<NextAspect, Para>
{
public:
	Visualizing( Para p )
		:
		Aspect<NextAspect, Para>{p}
	{
		std::cout << "Before Visualization aspect\n";
	}
	~Visualizing()
	{
		std::cout << "After Visualization aspect\n";
	}
};

template<class NextAspect, class Para>
struct Locking
	:
	Aspect<NextAspect, Para>
{
public:
	Locking( Para p )
		:
		Aspect<NextAspect, Para>{p}
	{
		std::cout << "Before Lock aspect\n";
	}
	~Locking() 
	{
		std::cout << "After Lock aspect\n";
	}
};

template <class NextAspect, class Para>
struct Logging
	:
	Aspect<NextAspect, Para>
{
public:
	Logging( Para p )
		:
		Aspect<NextAspect, Para>{p}
	{
		std::cout << "Before Log aspect\n";
	}
	~Logging() 
	{
		std::cout << "After Log aspect\n";
	}
};

template<class Aspect, class Para>
class AspectWeaver
{
	Para m_para;
public:
	AspectWeaver( Para p )
		:
		m_para{p}
	{

	}

	Aspect operator->() 
	{
		return Aspect(m_para);
	}
};


#define AW1(T,U) AspectWeaver<T<U, U>, U>
#define AW2(T,U,V) AspectWeaver<T<U<V, V>, V>, V>
#define AW3(T,U,V,X) AspectWeaver<T<U<V<X, X>, X>, X>, X>


int main()
{
	std::vector<int> vec;
	AW3( Visualizing, Locking, Logging, std::vector <int> *) Vec( &vec );
	//...
	Vec->push_back( 10 ); // Note use of -> operator instead of . operator
	Vec->push_back( 20 );

	std::system( "pause" );
	return 0;
}
