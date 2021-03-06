package org.val.win.business.impl.manager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.val.win.business.contract.manager.EmpruntManager;

import org.val.win.business.contract.manager.OuvrageManager;
import org.val.win.business.contract.manager.UtilisateurManager;
import org.val.win.model.bean.Emprunt;
import org.val.win.model.bean.Ouvrage;
import org.val.win.model.bean.Utilisateur;
import org.val.win.model.exception.NotFoundException;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:businessContextTest.xml"})
@Transactional
@Rollback(true)
public class EmpruntManagerImplTest {

    @Autowired
    private EmpruntManager empruntManager;
    @Autowired
    private UtilisateurManager utilisateurManager;
    @Autowired
    private OuvrageManager ouvrageManager;


    @Test
    public void emprunt() throws NotFoundException {
        Emprunt emprunt = new Emprunt();
        Utilisateur utilisateur = utilisateurManager.getUtilisateur("pnomtest", "mdptest");
        Ouvrage ouvrage = ouvrageManager.getOuvrage(15);
        empruntManager.emprunt(emprunt, utilisateur, ouvrage);
        Assertions.assertNotNull(empruntManager.getListEmpruntUtilisateur(utilisateur.getIdUtilisateur()));
    }

    @Test
    public void getListEmpruntUtil() {
        List<Emprunt> vListEmprunt = empruntManager.getListEmpruntUtilisateur(2);
        Assertions.assertNotNull(vListEmprunt);
    }

    @Test
    public void getEmprunt() throws NotFoundException {
        Emprunt vEmprunt = empruntManager.getEmprunt(1);
        Assertions.assertNotNull(vEmprunt);
    }

    @Test
    public void prolongerEmprunt() throws NotFoundException {
        Emprunt vEmprunt = empruntManager.getEmprunt(2);
        empruntManager.prolongerEmprunt(vEmprunt);
        Assertions.assertTrue(vEmprunt.getEtat() == "Prolongé");
    }

    @Test
    public void fermerEmprunt() throws NotFoundException {
        Emprunt vEmprunt = empruntManager.getEmprunt(1);
        Utilisateur vUtilisateur = utilisateurManager.getUtilisateur(vEmprunt.getIdUtilisateur());
        empruntManager.fermerEmprunt(vUtilisateur, vEmprunt);
        Assert.assertTrue(vEmprunt.getEtat() == "Terminé");
    }

    @Test
    public void retardEmprunt() throws NotFoundException {
        Emprunt vEmprunt = empruntManager.getEmprunt(1);
        empruntManager.retardEmprunt(vEmprunt);
        Assert.assertTrue(vEmprunt.getEtat() == "Retard");
    }
}