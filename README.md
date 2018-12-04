# Application execution:

java -jar smart-xml-analyzer-1.0.jar <original_document_path> <comparable_document_path> <target_element_id>

the last parameter is optional(the default target-element-id value is 'make-everything-ok-button')

#Results:

- sample-1-evil-gemini.html

```
html[0]>body[1]>div[0]>div[1]>div[2]>div[0]>div[0]>div[1]>a[1]
The decision about potential candidate was made based on:
Attribute 'class' with value 'btn btn-success' matched
Attribute 'href' with value '#ok' matched
Attribute 'title' with value 'Make-Button' matched
Attribute 'onclick' with value 'javascript:window.okDone(); return false;' matched
Attribute 'title' with value 'Make-Button' matched
Attribute 'class' with value 'btn btn-success' matched
Original element's text matched with the comparable one's text.
Total similarity score is: 9
```

- sample-2-container-and-clone.html

```
html[0]>body[1]>div[0]>div[1]>div[2]>div[0]>div[0]>div[1]>div[0]>a[0]
The decision about potential candidate was made based on:
Attribute 'rel' with value 'next' matched
Attribute 'href' with value '#ok' matched
Attribute 'title' with value 'Make-Button' matched
Original element's text matched with the comparable one's text.
Total similarity score is: 9
```

- sample-3-the-escape.html

```
html[0]>body[1]>div[0]>div[1]>div[2]>div[0]>div[0]>div[2]>a[0]
The decision about potential candidate was made based on:
Attribute 'rel' with value 'next' matched
Attribute 'href' with value '#ok' matched
Attribute 'onclick' with value 'javascript:window.okDone(); return false;' matched
Attribute 'rel' with value 'next' matched
Attribute 'href' with value '#ok' matched
Attribute 'class' with value 'btn btn-success' matched
Total similarity score is: 8
```

- sample-4-the-mash.html

```
html[0]>body[1]>div[0]>div[1]>div[2]>div[0]>div[0]>div[2]>a[0]
The decision about potential candidate was made based on:
Attribute 'rel' with value 'next' matched
Attribute 'href' with value '#ok' matched
Attribute 'title' with value 'Make-Button' matched
Attribute 'class' with value 'btn btn-success' matched
Total similarity score is: 8
```
