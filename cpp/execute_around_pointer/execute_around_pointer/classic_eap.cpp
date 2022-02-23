#include <iostream>
#include <vector>


class VectorAspect
{
	std::vector<int>* m_pAspect;
public:
	VectorAspect( std::vector<int>* v )
		:
		m_pAspect{v}
	{

	}

	class Vector final
	{
		std::vector<int>* m_vec;
	public:
		Vector( std::vector<int>* v )
			:
			m_vec{v}
		{
			std::cout << "Before size is: "
				<< m_vec->size()
				<< '\n';
		}

		~Vector() noexcept
		{
			std::cout << "After size is: "
				<< m_vec->size()
				<< '\n';
		}
		
		std::vector<int>* operator->()
		{
			return m_vec;
		}
	};

	Vector operator->()
	{
		return Vector{m_pAspect};
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
		std::cout << *it
			<< '\n';
	}
	std::cout << '\n';
	for ( std::vector<int>::const_iterator it = veca->begin(); it < veca->end(); ++it )
	{
		std::cout << *it
			<< '\n';
	}

#if defined _DEBUG && !defined NDEBUG
	while ( !getchar() );
#endif
	return EXIT_SUCCESS;
}