/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author alumno
 *
 */
public class ReusablePoolTest {

		
	private ReusablePool pool;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		pool = ReusablePool.getInstance();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		pool = null;
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		ReusablePool pool = ReusablePool.getInstance();
		// No es nulo
		assertNotNull(pool); 
		// el objeto devuelto es una instancia de ReublePool
		assertTrue(pool instanceof ReusablePool);
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */
	@Test
	public void testAcquireReusable() {
        ReusablePool reusablePool = ReusablePool.getInstance();
        
        try {
            Reusable reusableInstance = reusablePool.acquireReusable();
            
            // Asegura que la instancia no es nula
            assertNotNull("Se adquirió un objeto reusable", reusableInstance);
            
            // Asegura que la instancia devuelta es de tipo Reusable
            assertTrue(reusableInstance instanceof Reusable);
            
            // Caso 1: Se adquiere un objeto cuando reusables.size() > 0
            Reusable reusableInstance1 = reusablePool.acquireReusable();
            assertNotNull("Se adquirió un objeto reusable", reusableInstance1);

              
        } catch (NotFreeInstanceException e) {
            fail("No se esperaba la excepción NotFreeInstanceException");
        }
  
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	public void testReleaseReusable() {
		try {
            ReusablePool pool = ReusablePool.getInstance();
            Reusable reusableInstance = pool.acquireReusable();

            // Liberar la instancia
            pool.releaseReusable(reusableInstance);

            // Verificar que la instancia liberada está en el pool después de la liberación
            assertTrue("La instancia de Reusable debería estar en el pool después de ser liberada", pool.getReusables().contains(reusableInstance));

        } catch (NotFreeInstanceException | DuplicatedInstanceException e) {
            fail("No se esperaba ninguna excepción");
        }
    }

}
