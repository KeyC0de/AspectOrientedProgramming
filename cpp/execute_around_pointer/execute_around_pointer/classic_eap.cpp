#include <iostream>
#include <vector>


class VectorAspect
{
	std::vector<int>* m_vec;
public:
	VectorAspect( std::vector<int>* v )
		:
		m_vec( v )
	{}

	class proxy final
	{
		std::vector<int>* v_;
	public:
		proxy( std::vector<int>* v )
			:
			v_{v}
		{
			std::cout << "Before size is: " << v_->size() << '\n';
		}
		~proxy() noexcept
		{
			std::cout << "After size is: " << v_->size() << '\n';
		}

		std::vector<int>* operator->()
		{
			return v_;
		}
	};

	proxy operator->()
	{
		return proxy{m_vec};
	}
};


int main()
{
	std::vector<int> vec;
	VectorAspect veca{&vec};	// use this "Aspect" vector to operate on your vector
	// ...
	veca->push_back( 10 );		// Note use of -> operator instead of . operator
	veca->push_back( 20 );
	veca->push_back( 202 );
	veca->push_back( 200 );

	for ( std::vector<int>::const_iterator it = vec.begin(); it < vec.end(); ++it )
	{
		std::cout << *it << '\n';
	}
	std::cout << '\n';
	for ( std::vector<int>::const_iterator it = veca->begin(); it < veca->end(); ++it )
	{
		std::cout << *it << '\n';
	}

	std::system( "pause" );
	return 0;
}