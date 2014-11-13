# Guice commons-configuration module

This project implements a missing feature from Guice dependency framework to allow for injecting configuration values comming from all sort of sources,
for example system properties, property files, JNDI and so on. For a full list of configuration sources please refer to the [Apache Commons Configuration](http://commons.apache.org/proper/commons-configuration/) web site.

## Motivation

It's been a while since I last worked with Guice and I should think that such small inconviniences like missing runtime configuration would have already been sorted. And as a matter of fact it has been.
There's a project called [governator](https://github.com/Netflix/governator) that addresses this issue. The problem with governator is that it's way more than just configuration piece for Guice and if you
don't want to include the whole shebang you're pretty much left out in the cold as there's no other project (that I know of) that does it well enough. Now there is :)

## What's in the package

The project consists of 3 elements
 - a guice module (CommonsConfigurationModule) to be installed as usual
 - an annotation (@Value) that instructs the system to inject a configured value
 - a proxy class (Supplier) that is more/less a provider for values that can change in runtime

## Basic usage

Normally you'd start using this module by first annotating a field in a class with @Value like so:

```
public class MyObject {
	@Value("my.value")
	private String value = "Some default value";
}
```

Next to get the value injected from, let's say, system properties you'd instantiate the CommonsConfigurationModule and pass it on to the injector:

```
Configuration config = new SystemConfiguration();
Injector injector = Guice.createInjector(new CommonsConfigurationModule(config));
```

In case you'd like to use more than one source of configuration options you'd have to use the CompositeConfiguration:

```
Configuration config = new CompositeConfiguration(Arrays.asList(
	new SystemConfiguration(), new PropertiesConfiguration("some.properties")));
```
