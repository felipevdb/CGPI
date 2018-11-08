/*package draw_tools;

public class Draw_Clip {
	int a = ((int) (a+0.5)); 
	String LEFT_EDGE =  "0x1"; 
	String RIGHT_EDGE =  "0x2"; 
	String BOTTOM_EDGE =  "0x4"; 
	String TOP_EDGE  =   "0x8"; 
	/*   
	    Points encoded as 0000 are completely inside the clip rectangle; 
	    all others are outside at least one edge. If OR'ing two codes is  
	    FALSE (no bits are set in either code), the line can be Accepted. If 
	    the AND operation between two codes is TRUE, the line defined by those 
	    endpoints is completely outside the clip region and can be Rejected. 
	
	INSIDE(a)   (!a) 
	REJECT(a,b) (a&b) 
	ACCEPT(a,b) (!(a|b))
	
	public String encode (wcPt2 pt, dcPt winMin, dcPt winMax) 
	{ 
	   String code= "0x00"; 
	   if (pt.x < winMin.x) 
	     code = LEFT_EDGE; 
	   if (pt.x > winMax.x) 
	     code = RIGHT_EDGE; 
	   if (pt.y < winMin.y) 
	     code = BOTTOM_EDGE; 
	   if (pt.y > winMax.y) 
	     code = TOP_EDGE; 
	   return (code); 
	} 
	void swapPts (wcPt2 * p1, wcPt2 * p2) 
	{ 
	   wcPt2 tmp; 
	   tmp = *p1, *p1 = *p2; *p2 = tmp; 
	} 
	void swapCodes (String * c1, String * c2) 
	{ 
	   char tmp; 
	   tmp = *c1; *c1 = *c2; *c2 = tmp; 
	} 
}

void clipLine (dcPt winMin, dcPt winMax, wcPt2 p1, wcPt2 p2){ 
	   String code1, code2; 
	   boolean done = false, draw = false; 
	   float m; 
	   while (!done)  { 
	      code1 = encode (p1, winMin, winMax); 
	      code2 = encode (p2, winMin, winMax); 
	      if ((code1 != a) || (code2 != b)) { // Totalmente dentro 
	         done = true; 
	         draw = true; 
	      } 
	      else if ((code1 == a) && (code2 == b)) // Totalmente fora 
	         done = TRUE; 
	      else { 
	         if ((code1 != a))  { /* Ensure that p1 is outside window 
	            swapPts (&p1, &p2); 
	            swapCodes (&code1, &code2); 
	         } 
	         if (p2.x != p1.x) 
	            /* Use slope (m) to find line-clipEdge intersections 
	            m = (p2.y - p1.y) / (p2.x - p1.x); 
	         if (code1 == LEFT_EDGE)  { 
	            p1.y += (winMin.x - p1.x) + m; 
	            p1.x = winMin.x; 
	         } 
	         else if (code1 == RIGHT_EDGE)  { 
	            p1.y += (winMax.x - p1.x) * m; 
	            p1.x = winMax.x; 
	         } 
	         else if (code1 == BOTTOM_EDGE)  { 
	            /* Need to update pl.x for non-vertical lines only 
	            if (p2.x != p1.x) 
	               p1.x += (winMin.y - p1.y) / m; 
	            p1.y = winMin.y; 
	         } 
	         else if (code1 == TOP_EDGE)  { 
	            if (p2.x != p1.x) 
	               p1.x += (winMax.y - p1.y) / m; 
	            p1.y = winMax.y; 
	         } 
	      } 
	   } 
	   if (draw) 
	      lineDDA (ROUND (p1.x), ROUND (p1.y), ROUND (p2.x), ROUND (p2.y)); 
	} */