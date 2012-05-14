/*******************************************************************************
 * Copyright (c) 2011-2012 Luc Moreau
 *******************************************************************************/
grammar PROV_N;

options {
  language = Java;
  output=AST;
}

tokens {
    ID; ATTRIBUTE; ATTRIBUTES; IRI; QNAM; STRING; TYPEDLITERAL; INT; 
    BUNDLE; BUNDLES; NAMEDBUNDLE; EXPRESSIONS; NAMESPACE; DEFAULTNAMESPACE; NAMESPACES; PREFIX; 

    /* Component 1 */
    ENTITY; ACTIVITY; WGB; USED; WSB; WEB; WINVB; WIB; 
    TIME; START; END;

    /* Component 2 */
    AGENT; PLAN; WAT; WAW; AOBO; 
    /* Component 3 */
    WDF; WRO; ORIGINALSOURCE; WQF; TRACEDTO; 
    /* Component 4 */
    SPECIALIZATION; ALTERNATE; 
    /* Component 5 */
    DBIF; DBRF; KES; KEYS; VALUES; MEM; TRUE; FALSE; UNKNOWN;
    /* Component 6 */
    NOTE; HAN;
}

@header {
package org.openprovenance.prov.notation;
}

@lexer::header {
package org.openprovenance.prov.notation;
}

@members{
 public static boolean qnameDisabled=false; }

bundle
	:	ML_COMMENT* 'bundle' 
        (namespaceDeclarations)?
		(expression | ML_COMMENT | SL_COMMENT)*
        (namedBundle (namedBundle | ML_COMMENT | SL_COMMENT)*)?
		'endBundle'
      -> {$namespaceDeclarations.tree==null}? ^(BUNDLE ^(NAMESPACES) ^(EXPRESSIONS expression*) ^(BUNDLES namedBundle*))
      -> ^(BUNDLE namespaceDeclarations? ^(EXPRESSIONS expression*) ^(BUNDLES namedBundle*))
    ;

namedBundle
 	:	'bundle' identifier
        (namespaceDeclarations)?
		(expression | ML_COMMENT | SL_COMMENT)*
		'endBundle'
      -> {$namespaceDeclarations.tree==null}? ^(NAMEDBUNDLE identifier ^(NAMESPACES) ^(EXPRESSIONS expression*))
      -> ^(NAMEDBUNDLE identifier namespaceDeclarations? ^(EXPRESSIONS expression*))
	;


namespaceDeclarations :
        (defaultNamespaceDeclaration | namespaceDeclaration) namespaceDeclaration*
        -> ^(NAMESPACES defaultNamespaceDeclaration? namespaceDeclaration*)
    ;


/**
  The following production uses ANTLR directives to disactivate the
  production QUALNAME to guarantee that ensure that PREFX token is
  generated in this context only.

  A global static boolean variable (qnameDisabled) is declared in the
  parser, set to true when entering this production and set to false
  on exit.  This variable is use in the lexer to disable the QUALNAME
  production.

  Sorry, that's the only way I have found to do this. Obviously, this
  prevents concurrent execution of the parser/lexer. */

namespaceDeclaration 
@init { qnameDisabled = true; }
@after { qnameDisabled = false; } :
        'prefix' PREFX namespace
        -> ^(NAMESPACE ^(PREFIX PREFX) namespace)
    ;
namespace :
        IRI_REF
    ;

defaultNamespaceDeclaration :
        'default' IRI_REF
        ->  ^(DEFAULTNAMESPACE IRI_REF)
    ;

expression
@init { qnameDisabled = false; } :
        (   /* component 1 */

           entityExpression | activityExpression | generationExpression  | usageExpression
         | startExpression | endExpression | invalidationExpression | communicationExpression

            /* component 2 */
        
        | agentExpression |  associationExpression | attributionExpression | responsibilityExpression

            /* component 3 */

        | derivationExpression | tracedToExpression | hadOriginalSourceExpression | quotationExpression | revisionExpression

            /* component 4 */

        | alternateExpression | specializationExpression

            /* component 5 */

        | insertionExpression | removalExpression | membershipExpression 


            /* component 6 */ 
        | noteExpression | hasAnnotationExpression
        )
	;

/*
        Component 1: Entities/Activities

*/

entityExpression
	:	'entity' '(' identifier optionalAttributeValuePairs ')'
        -> ^(ENTITY identifier optionalAttributeValuePairs)
	;


activityExpression
	:	'activity' '(' identifier (',' (s=time | '-' ) ',' (e=time | '-'))? optionalAttributeValuePairs ')'
        -> ^(ACTIVITY identifier ^(START $s?) ^(END $e?) optionalAttributeValuePairs )
	;

generationExpression
	:	'wasGeneratedBy' '(' id0=optionalIdentifier id2=identifier (',' id1=identifierOrMarker ',' ( time | '-' ))? optionalAttributeValuePairs ')'
      -> {$id1.tree==null}? ^(WGB ^(ID $id0?) $id2 ^(ID)  ^(TIME time?) optionalAttributeValuePairs)
      -> ^(WGB ^(ID $id0?) $id2 $id1  ^(TIME time?) optionalAttributeValuePairs)
	;

optionalIdentifier
    : ((id0=identifier | '-') ';')?
     -> identifier?
    ;

identifierOrMarker
    : ((identifier) | '-')
     -> identifier?
    ;


usageExpression
	:	'used' '(' id0=optionalIdentifier  id2=identifier ',' id1=identifier (',' ( time | '-' ))? optionalAttributeValuePairs ')'
      -> ^(USED ^(ID $id0?)  $id2 $id1 ^(TIME time?) optionalAttributeValuePairs)
	;

startExpression
	:	'wasStartedBy' '(' id0=optionalIdentifier id2=identifier (',' id1=identifierOrMarker ',' id3=identifierOrMarker ',' ( time | '-' ))? optionalAttributeValuePairs ')'
      -> {$id1.tree==null && $id3.tree==null}? ^(WSB ^(ID $id0?) $id2 ^(ID) ^(ID)  ^(TIME time?) optionalAttributeValuePairs)
      -> {$id1.tree==null && $id3.tree!=null}? ^(WSB ^(ID $id0?) $id2 ^(ID) $id3 ^(TIME time?) optionalAttributeValuePairs)
      -> {$id3.tree==null}? ^(WSB ^(ID $id0?) $id2 $id1 ^(ID) ^(TIME time?) optionalAttributeValuePairs)
      -> ^(WSB ^(ID $id0?) $id2 $id1 $id3 ^(TIME time?) optionalAttributeValuePairs)
	;

endExpression
	:	'wasEndedBy' '(' id0=optionalIdentifier id2=identifier (',' id1=identifierOrMarker ',' id3=identifierOrMarker ',' ( time | '-' ))? optionalAttributeValuePairs ')'
      -> {$id1.tree==null && $id3.tree==null}? ^(WEB ^(ID $id0?) $id2 ^(ID) ^(ID)  ^(TIME time?) optionalAttributeValuePairs)
      -> {$id1.tree==null && $id3.tree!=null}? ^(WEB ^(ID $id0?) $id2 ^(ID) $id3 ^(TIME time?) optionalAttributeValuePairs)
      -> {$id3.tree==null}? ^(WEB ^(ID $id0?) $id2 $id1 ^(ID) ^(TIME time?) optionalAttributeValuePairs)
      -> ^(WEB ^(ID $id0?) $id2 $id1 $id3 ^(TIME time?) optionalAttributeValuePairs)
	;


invalidationExpression
	:	'wasInvalidatedBy' '(' id0=optionalIdentifier id2=identifier (',' id1=identifierOrMarker ',' ( time | '-' ))? optionalAttributeValuePairs ')'
      -> {$id1.tree==null}? ^(WINVB ^(ID $id0?) $id2 ^(ID)  ^(TIME time?) optionalAttributeValuePairs)
      -> ^(WINVB ^(ID $id0?) $id2 $id1  ^(TIME time?) optionalAttributeValuePairs)
	;


communicationExpression
	:	'wasInformedBy' '(' id0=optionalIdentifier id2=identifier ',' id1=identifier optionalAttributeValuePairs ')'
      -> ^(WIB ^(ID $id0?) $id2 $id1 optionalAttributeValuePairs)
	;



/*
        Component 2: Agents and Responsibility

*/

agentExpression
	:	'agent' '(' identifier optionalAttributeValuePairs	')' 
        -> ^(AGENT identifier optionalAttributeValuePairs )
	;

attributionExpression
	:	'wasAttributedTo' '('  id0=optionalIdentifier e=identifier ',' ag=identifier optionalAttributeValuePairs ')'
      -> ^(WAT  ^(ID $id0?) $e $ag optionalAttributeValuePairs)
	;

associationExpression
	:	'wasAssociatedWith' '('  id0=optionalIdentifier a=identifier ',' ag=identifierOrMarker (',' pl=identifierOrMarker)? optionalAttributeValuePairs ')'
      -> {$ag.tree==null}? ^(WAW ^(ID $id0?) $a ^(ID) ^(PLAN $pl?) optionalAttributeValuePairs)
      -> ^(WAW ^(ID $id0?) $a $ag? ^(PLAN $pl?) optionalAttributeValuePairs)
	;

responsibilityExpression
	:	'actedOnBehalfOf' '('   id0=optionalIdentifier ag2=identifier ',' ag1=identifier (','  a=identifierOrMarker)? optionalAttributeValuePairs ')'
      -> {$a.tree==null}? ^(AOBO  ^(ID $id0?) $ag2 $ag1 ^(ID) optionalAttributeValuePairs)
      -> ^(AOBO  ^(ID $id0?) $ag2 $ag1 $a? optionalAttributeValuePairs)
	;



/*
        Component 3: Derivations

*/ 


derivationExpression
	:	'wasDerivedFrom' '(' id0=optionalIdentifier id2=identifier ',' id1=identifier (',' a=identifierOrMarker ',' g2=identifierOrMarker ',' u1=identifierOrMarker )?	optionalAttributeValuePairs ')'
      -> {$a.tree==null && $g2.tree==null && $u1.tree==null}?
          ^(WDF ^(ID $id0?) $id2 $id1 ^(ID) ^(ID) ^(ID) optionalAttributeValuePairs)
      -> {$a.tree!=null && $g2.tree==null && $u1.tree==null}?
          ^(WDF ^(ID $id0?) $id2 $id1 $a ^(ID) ^(ID) optionalAttributeValuePairs)
      -> {$a.tree==null && $g2.tree!=null && $u1.tree==null}?
          ^(WDF ^(ID $id0?) $id2 $id1 ^(ID) $g2 ^(ID) optionalAttributeValuePairs)
      -> {$a.tree!=null && $g2.tree!=null && $u1.tree==null}?
          ^(WDF ^(ID $id0?) $id2 $id1 $a $g2 ^(ID) optionalAttributeValuePairs)

      -> {$a.tree==null && $g2.tree==null && $u1.tree!=null}?
          ^(WDF ^(ID $id0?) $id2 $id1 ^(ID) ^(ID) $u1 optionalAttributeValuePairs)
      -> {$a.tree!=null && $g2.tree==null && $u1.tree!=null}?
          ^(WDF ^(ID $id0?) $id2 $id1 $a ^(ID) $u1 optionalAttributeValuePairs)
      -> {$a.tree==null && $g2.tree!=null && $u1.tree!=null}?
          ^(WDF ^(ID $id0?) $id2 $id1 ^(ID) $g2 $u1 optionalAttributeValuePairs)
      -> ^(WDF ^(ID $id0?) $id2 $id1 $a $g2 $u1 optionalAttributeValuePairs)
	;


revisionExpression
	:	'wasRevisionOf' '('  id0=optionalIdentifier id2=identifier ',' id1=identifier (',' a=identifierOrMarker ',' g2=identifierOrMarker ',' u1=identifierOrMarker )?	optionalAttributeValuePairs ')'
      -> {$a.tree==null && $g2.tree==null && $u1.tree==null}?
          ^(WRO ^(ID $id0?) $id2 $id1 ^(ID) ^(ID) ^(ID) optionalAttributeValuePairs)
      -> {$a.tree!=null && $g2.tree==null && $u1.tree==null}?
          ^(WRO ^(ID $id0?) $id2 $id1 $a ^(ID) ^(ID) optionalAttributeValuePairs)
      -> {$a.tree==null && $g2.tree!=null && $u1.tree==null}?
          ^(WRO ^(ID $id0?) $id2 $id1 ^(ID) $g2 ^(ID) optionalAttributeValuePairs)
      -> {$a.tree!=null && $g2.tree!=null && $u1.tree==null}?
          ^(WRO ^(ID $id0?) $id2 $id1 $a $g2 ^(ID) optionalAttributeValuePairs)
      -> {$a.tree==null && $g2.tree==null && $u1.tree!=null}?
          ^(WRO ^(ID $id0?) $id2 $id1 ^(ID) ^(ID) $u1 optionalAttributeValuePairs)
      -> {$a.tree!=null && $g2.tree==null && $u1.tree!=null}?
          ^(WRO ^(ID $id0?) $id2 $id1 $a ^(ID) $u1 optionalAttributeValuePairs)
      -> {$a.tree==null && $g2.tree!=null && $u1.tree!=null}?
          ^(WRO ^(ID $id0?) $id2 $id1 ^(ID) $g2 $u1 optionalAttributeValuePairs)
      -> ^(WRO ^(ID $id0?) $id2 $id1 $a $g2 $u1 optionalAttributeValuePairs)
	;



quotationExpression
	:	'wasQuotedFrom' '('  id0=optionalIdentifier id2=identifier ',' id1=identifier (',' a=identifierOrMarker ',' g2=identifierOrMarker ',' u1=identifierOrMarker )?	optionalAttributeValuePairs ')'
      -> {$a.tree==null && $g2.tree==null && $u1.tree==null}?
          ^(WQF ^(ID $id0?) $id2 $id1 ^(ID) ^(ID) ^(ID) optionalAttributeValuePairs)
      -> {$a.tree!=null && $g2.tree==null && $u1.tree==null}?
          ^(WQF ^(ID $id0?) $id2 $id1 $a ^(ID) ^(ID) optionalAttributeValuePairs)
      -> {$a.tree==null && $g2.tree!=null && $u1.tree==null}?
          ^(WQF ^(ID $id0?) $id2 $id1 ^(ID) $g2 ^(ID) optionalAttributeValuePairs)
      -> {$a.tree!=null && $g2.tree!=null && $u1.tree==null}?
          ^(WQF ^(ID $id0?) $id2 $id1 $a $g2 ^(ID) optionalAttributeValuePairs)
      -> {$a.tree==null && $g2.tree==null && $u1.tree!=null}?
          ^(WQF ^(ID $id0?) $id2 $id1 ^(ID) ^(ID) $u1 optionalAttributeValuePairs)
      -> {$a.tree!=null && $g2.tree==null && $u1.tree!=null}?
          ^(WQF ^(ID $id0?) $id2 $id1 $a ^(ID) $u1 optionalAttributeValuePairs)
      -> {$a.tree==null && $g2.tree!=null && $u1.tree!=null}?
          ^(WQF ^(ID $id0?) $id2 $id1 ^(ID) $g2 $u1 optionalAttributeValuePairs)
      -> ^(WQF ^(ID $id0?) $id2 $id1 $a $g2 $u1 optionalAttributeValuePairs)
	;


hadOriginalSourceExpression
	:	'hadOriginalSource' '('  id0=optionalIdentifier id2=identifier ',' id1=identifier (',' a=identifierOrMarker ',' g2=identifierOrMarker ',' u1=identifierOrMarker )?	optionalAttributeValuePairs ')'
      -> {$a.tree==null && $g2.tree==null && $u1.tree==null}?
          ^(ORIGINALSOURCE ^(ID $id0?) $id2 $id1 ^(ID) ^(ID) ^(ID) optionalAttributeValuePairs)
      -> {$a.tree!=null && $g2.tree==null && $u1.tree==null}?
          ^(ORIGINALSOURCE ^(ID $id0?) $id2 $id1 $a ^(ID) ^(ID) optionalAttributeValuePairs)
      -> {$a.tree==null && $g2.tree!=null && $u1.tree==null}?
          ^(ORIGINALSOURCE ^(ID $id0?) $id2 $id1 ^(ID) $g2 ^(ID) optionalAttributeValuePairs)
      -> {$a.tree!=null && $g2.tree!=null && $u1.tree==null}?
          ^(ORIGINALSOURCE ^(ID $id0?) $id2 $id1 $a $g2 ^(ID) optionalAttributeValuePairs)
      -> {$a.tree==null && $g2.tree==null && $u1.tree!=null}?
          ^(ORIGINALSOURCE ^(ID $id0?) $id2 $id1 ^(ID) ^(ID) $u1 optionalAttributeValuePairs)
      -> {$a.tree!=null && $g2.tree==null && $u1.tree!=null}?
          ^(ORIGINALSOURCE ^(ID $id0?) $id2 $id1 $a ^(ID) $u1 optionalAttributeValuePairs)
      -> {$a.tree==null && $g2.tree!=null && $u1.tree!=null}?
          ^(ORIGINALSOURCE ^(ID $id0?) $id2 $id1 ^(ID) $g2 $u1 optionalAttributeValuePairs)
      -> ^(ORIGINALSOURCE ^(ID $id0?) $id2 $id1 $a $g2 $u1 optionalAttributeValuePairs)
	;



tracedToExpression
	:	'tracedTo' '('  id0=optionalIdentifier id2=identifier ',' id1=identifier optionalAttributeValuePairs ')'
      -> ^(TRACEDTO ^(ID $id0?) $id2 $id1 optionalAttributeValuePairs)
	;


/*
        Component 4: Alternate entities

*/

alternateExpression
	:	'alternateOf' '('  identifier ',' identifier ')'
      -> ^(ALTERNATE identifier+)
	;

specializationExpression
	:	'specializationOf' '('  identifier ',' identifier ')'
      -> ^(SPECIALIZATION identifier+)
	;

/*
        Component 5: Collections

TODO: literal used in these production needs to disable qname, to allow for intliteral

*/

insertionExpression
	:	'derivedByInsertionFrom' '('  id0=optionalIdentifier id2=identifier ',' id1=identifier ',' keyEntitySet optionalAttributeValuePairs ')'
      -> ^(DBIF ^(ID $id0?) $id2 $id1 keyEntitySet  optionalAttributeValuePairs)
	;

removalExpression
	:	'derivedByRemovalFrom' '('  id0=optionalIdentifier id2=identifier ',' id1=identifier ',' '{' literal (',' literal)* '}' optionalAttributeValuePairs ')'
      -> ^(DBRF ^(ID $id0?) $id2 $id1 ^(KEYS literal*)  optionalAttributeValuePairs)
	;

/* TODO: specify complete as optional boolean */
membershipExpression
	:	( 'memberOf' '('  id0=optionalIdentifier  id1=identifier ',' keyEntitySet ',' 'true' optionalAttributeValuePairs ')'
      -> ^(MEM ^(ID $id0?) $id1 keyEntitySet  ^(TRUE) optionalAttributeValuePairs)
         |          
          'memberOf' '('  id0=optionalIdentifier  id1=identifier ',' keyEntitySet ',' 'false' optionalAttributeValuePairs ')'
      -> ^(MEM ^(ID $id0?) $id1 keyEntitySet  ^(FALSE) optionalAttributeValuePairs)
         |          
          'memberOf' '('  id0=optionalIdentifier  id1=identifier ',' keyEntitySet optionalAttributeValuePairs ')'
      -> ^(MEM ^(ID $id0?) $id1 keyEntitySet  ^(UNKNOWN) optionalAttributeValuePairs)
        )
	;

keyEntitySet
    : '{'  '(' literal ',' val=identifier  ')' ( ','  '(' literal ',' val=identifier  ')' )* '}'
      -> ^(KES ^(KEYS literal+) ^(VALUES identifier+))
    ;

/* TODO */

/*
        Component 6: Annotations

*/

noteExpression
	:	'note' '(' identifier optionalAttributeValuePairs	')' 
        -> ^(NOTE identifier optionalAttributeValuePairs )
	;

hasAnnotationExpression
	:	'hasAnnotation' '(' identifier ',' identifier	')' 
        -> ^(HAN identifier+ )
	;






optionalAttributeValuePairs
    :
    (',' '[' attributeValuePairs ']')?
        -> ^(ATTRIBUTES attributeValuePairs?)
    ;


identifier
	:
        QUALNAME -> ^(ID QUALNAME)
	;

attribute
	:
        QUALNAME
	;

attributeValuePairs
	:
        (  | attributeValuePair ( ','! attributeValuePair )* )
	;


/*
QUALNAME and INTLITERAL are conflicting.  To avoid the conflict in
literal, QUALNAME rule is disabled once an attribute has been parsed, to
give priority to INTLITERAL.
  */

attributeValuePair
@after { qnameDisabled = false; }

	:
        attribute { qnameDisabled = true; } '='  literal  -> ^(ATTRIBUTE attribute literal)
	;


time
	:
        xsdDateTime
	;


/* TODO: complete grammar of Literal */


literal :
        (STRINGLITERAL -> ^(STRING STRINGLITERAL) |
         INTLITERAL -> ^(INT INTLITERAL) |
         STRINGLITERAL { qnameDisabled = false; } '%%' datatype -> ^(TYPEDLITERAL STRINGLITERAL datatype) |
         { qnameDisabled = false; } '\'' QUALNAME '\'' -> ^(TYPEDLITERAL QUALNAME) | )
	;

datatype:
        (IRI_REF -> ^(IRI IRI_REF)
        |
         QUALNAME -> ^(QNAM QUALNAME))
	;

	

/* The production here are based on the SPARQL grammar availabe from:
   http://antlr.org/grammar/1200929755392/Sparql.g */




INTLITERAL:
    '-'? DIGIT+
    ;

STRINGLITERAL : '"' (options {greedy=false;} : ~('"' | '\\' | EOL) | ECHAR)* '"';


/* This production uses a "Disambiguating Semantic Predicates"
   checking whether we are in scope of a declaration/literal or not. If so,
   rule QUALNAME is disabled, to the benefit of PREFX/INTLITERAL. */

QUALNAME:
  { !PROV_NParser.qnameDisabled }?
    (PN_PREFIX ':')? PN_LOCAL | PN_PREFIX ':'
        
  ;

/* The order of the two rules (QUALNAME/PREFX) is crucial. By default, QUALNAME should be used
   unless we are in the context of a declaration. */
PREFX:
    PN_PREFIX
  ;



fragment
ECHAR : '\\' ('t' | 'b' | 'n' | 'r' | 'f' | '\\' | '"' | '\'');
 

/* Note PN_CHARS_BASE is same as NCNAMESTARTCHAR (XML Schema)
   http://www.w3.org/TR/rdf-sparql-query/#rPN_CHARS_U
   http://www.w3.org/2010/01/Turtle/#prod-turtle2-PN_CHARS_U
 */

fragment
PN_CHARS_U : PN_CHARS_BASE | '_';

/* Note: this is the same as NCNAMECHAR (XML Schema) except for '.' 
   http://www.w3.org/TR/rdf-sparql-query/#rPN_CHARS 
   http://www.w3.org/2010/01/Turtle/#prod-turtle2-PN_CHARS

*/

fragment
PN_CHARS
    : PN_CHARS_U
    | MINUS
    | DIGIT
    | '\u00B7' 
    | '\u0300'..'\u036F'
    | '\u203F'..'\u2040'
    ;

/* 
http://www.w3.org/TR/rdf-sparql-query/#rPN_PREFIX
http://www.w3.org/2010/01/Turtle/#prod-turtle2-PN_PREFIX
*/

fragment
PN_PREFIX : PN_CHARS_BASE ((PN_CHARS|DOT)* PN_CHARS)?;

/* Note PN_LOCAL allows for start with a digit 

  same as http://www.w3.org/TR/rdf-sparql-query/#rPN_LOCAL, except that here we accept '/', '@'
  same http://www.w3.org/2010/01/Turtle/#prod-turtle2-PN_LOCAL, except that here we accept '/', '@'
*/
fragment
PN_LOCAL:
  (PN_CHARS_U | DIGIT | PN_CHARS_OTHERS)  ((PN_CHARS| PN_CHARS_OTHERS |{    
                    	                       if (input.LA(1)=='.') {
                    	                          int LA2 = input.LA(2);
                    	       	                  if (!((LA2>='-' && LA2<='.')||(LA2>='0' && LA2<='9')||(LA2>='A' && LA2<='Z')||LA2=='_'||(LA2>='a' && LA2<='z')||LA2=='\u00B7'||(LA2>='\u00C0' && LA2<='\u00D6')||(LA2>='\u00D8' && LA2<='\u00F6')||(LA2>='\u00F8' && LA2<='\u037D')||(LA2>='\u037F' && LA2<='\u1FFF')||(LA2>='\u200C' && LA2<='\u200D')||(LA2>='\u203F' && LA2<='\u2040')||(LA2>='\u2070' && LA2<='\u218F')||(LA2>='\u2C00' && LA2<='\u2FEF')||(LA2>='\u3001' && LA2<='\uD7FF')||(LA2>='\uF900' && LA2<='\uFDCF')||(LA2>='\uFDF0' && LA2<='\uFFFD'))) {
                    	       	                     return;
                    	       	                  }
                    	                       }
                                           } DOT)* (PN_CHARS | PN_CHARS_OTHERS ))?
;

fragment PN_CHARS_OTHERS 
    : 
      PERCENT | '/' | '@' | '~' | '&' | '+' | '?' | '#' | '$'
    ;

fragment PERCENT
     :
     '%' HEX HEX
    ;

fragment HEX 
     : DIGIT | 'A' .. 'F' | 'a' .. 'f' 
    ;
/* 
   same as http://www.w3.org/TR/rdf-sparql-query/#rPN_CHARS_BASE  except for [#10000-#EFFFF], which Java doesn't seem to support
   same as http://www.w3.org/2010/01/Turtle/#prod-turtle2-PN_CHARS_BASE except for [#10000-#EFFFF], which Java doesn't seem to support

   Note PN_CHARS_BASE is same as NCNAMESTARTCHAR (XML Schema) except for '_' and ':' 
   http://www.w3.org/TR/REC-xml/#NT-NameStartChar
*/
fragment
PN_CHARS_BASE
    : 'A'..'Z'
    | 'a'..'z'
    | '\u00C0'..'\u00D6'
    | '\u00D8'..'\u00F6'
    | '\u00F8'..'\u02FF'
    | '\u0370'..'\u037D'
    | '\u037F'..'\u1FFF'
    | '\u200C'..'\u200D'
    | '\u2070'..'\u218F'
    | '\u2C00'..'\u2FEF'
    | '\u3001'..'\uD7FF'
    | '\uF900'..'\uFDCF'
    | '\uFDF0'..'\uFFFD'
    ;
    
fragment DIGIT: '0'..'9';

fragment
EOL : '\n' | '\r';
	
DOT : '.';

MINUS : '-';


/* Multiline comment */
ML_COMMENT
    :   '/*' (options {greedy=false;} : .)* '*/' {$channel=HIDDEN;}
    ;

/* Singleline comment */
SL_COMMENT : '//' (options{greedy=false;} : .)* EOL { $channel=HIDDEN; };


/* 
This lexer rule for comments handles multiline, nested comments
*/
COMMENT_CONTENTS
        :       '(:'
                {
                        $channel=98;
                }
                (       ~('('|':')
                        |       ('(' ~':') => '('
                        |       (':' ~')') => ':'
                        |       COMMENT_CONTENTS
                )*
                ':)'
        ;



WS : (' '| '\t'| EOL)+ { $channel=HIDDEN; };


IRI_REF
  :
  LESS
  ( options {greedy=false;}:
    ~(
      LESS
      | GREATER
      | '"'
      | OPEN_CURLY_BRACE
      | CLOSE_CURLY_BRACE
      | '|'
      | '^'
      | '\\'
      | '`'
      | ('\u0000'..'\u0020')
     )
  )*
  GREATER
  ;


LESS
  :
  '<'
  ;

GREATER
  :
  '>'
  ;
OPEN_CURLY_BRACE
  :
  '{'
  ;

CLOSE_CURLY_BRACE
  :
  '}'
  ;




xsdDateTime: IsoDateTime;



IsoDateTime: (DIGIT DIGIT DIGIT DIGIT '-' DIGIT DIGIT '-' DIGIT DIGIT 'T' DIGIT DIGIT ':' DIGIT DIGIT ':' DIGIT DIGIT ('.' DIGIT (DIGIT DIGIT?)?)? ('Z' | TimeZoneOffset)?)
    ;




TimeZoneOffset: ('+' | '-') DIGIT DIGIT ':' DIGIT DIGIT;




