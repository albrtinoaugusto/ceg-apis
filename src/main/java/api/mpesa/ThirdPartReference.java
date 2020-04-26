
package api.mpesa;

/**
 *
 * @author Augusto Marrengula */

public class ThirdPartReference
{
    
    public String generateReference()
    {
        //String reference = "1284O56"; new Generator().generateMpesaSaleKey();
        return new Generator().generateMpesaSaleKey();
    }
    
}
