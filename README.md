# ParsiAnalyzer
ParsiAnalyzer is an analysis plugin for Elasticsearch. Analysis is a process that consists of the following steps:

- Tokenizing a block of text into individual terms
- Normalizing these terms into a standard form

An analyzer is really just a wrapper that combines Character filters, Tokenizer, and Token filters. Elasticsearch provides many Built-in Analyzers but there's still room for improvement especially for Persian language. This plugin provides tools for tokenizing, normalizing and stemming Persian text.

## Key features
- Tokenize Persian text
  - Convert whitespaces to zero width nonjoiner (`نیم‌فاصله`) whenever it is necessary. for example,`می رود` to `می‌رود`.
  - Convert Persian punctuations to their English equivalent. for example,`۳/۱۴` to `۳.۱۴`
  - Tokenize Persian text by whitespaces and punctuations.
  
- Normalize Persian tokens into a single canonical form
  - Transform all forms of Yeh, Kaf, Heh, and Hamza to a unique form. for example,`براي` to `برای`.
  - Convert all Persian and Arabic numbers to their English equivalent. for example,`۱۴۳` to `143`.
  - Remove diacritic (`اِعراب`) from words. for example, `اَرّه` to `اره`.
  - Remove Kashida form words. for example, `بادبــــــادک` to `بادبادک`.
  
- Remove common Persian stop words
  - Persian stop words like `از`, `به` and etc will be removed.
  
- Stem Persian words
  - Remove common Persian suffixes. for example, `ها` or `ان`.
  
## Installation
To install the plugin for Elasticsearch 6.3.2, run this command:

```bin\elasticsearch-plugin install https://www.dropbox.com/s/2sczcj1372xsq7k/ParsiAnalyzer-%206.3.2.zip?dl=1```

## Usage
To see how this plugin works, you can use Elasticsearch's `analyze` API:
```
GET _analyze
{
  "analyzer" : "parsi",
  "text" : "روباه قهوه‌اي چابك از روی سگ تنبل می پرد"
}
```
This will give you these tokens: `[روباه,قهوه‌ای,چابک,روی,سگ,تنبل,می‌پرد]`

ParsiAnalyzer can be specified directly in the field mapping as follows: 
```
PUT /my_index
{
  "mappings": {
    "blog": {
      "properties": {
        "title": {
          "type":     "string",
          "analyzer": "parsi" 
        }
      }
    }
  }
}
```

# Contact me
**Email**: n.esmaielyfard [at] gmail.com
